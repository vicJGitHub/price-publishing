package com.hywa.pricepublish.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.enums.Module;
import com.hywa.pricepublish.representation.ResponseBase;
import java.io.OutputStream;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
//@Component
public class PermissionAuthConfig {

    @Autowired
    private AuthStrategy authStrategy;

    @Around("execution(* com.hywa.pricepublish.controller..*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object executeAround(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();

        //获取请求头里面用户的ID，此处只做简单处理，实际应该有加密，否则将会导致用户信息泄露，无法保证安全
        String userId = request.getHeader("userId");

        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Method realMethod = point.getTarget().getClass()
                .getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());

        Object obj = null;

        if (isHasPermission(realMethod, userId)) {
            obj = point.proceed();
        } else {
            response.setHeader("Content-type", "application/json; charset=UTF-8");
            OutputStream outputStream = response.getOutputStream();
            ResponseBase<Object> responseBase = new ResponseBase<>();
            responseBase.setRetHead(CommonEnum.FAILURE.getIndex(),
                    "Not allowed to pass, you do not have the authority");
            outputStream
                    .write(new ObjectMapper().writeValueAsString(responseBase).getBytes("UTF-8"));
        }
        return obj;
    }

    private boolean isHasPermission(Method realMethod, String userId) {
        try {
            if (realMethod.isAnnotationPresent(PermissionModule.class)) {
                PermissionModule permissionModule = realMethod
                        .getAnnotation(PermissionModule.class);
                Module[] modules = permissionModule.belong();
                //执行权限策略，判断用户权限
                return new AuthContext(authStrategy).execute(modules, userId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }
}
