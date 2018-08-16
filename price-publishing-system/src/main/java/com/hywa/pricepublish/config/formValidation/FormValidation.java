package com.hywa.pricepublish.config.formValidation;

import com.hywa.pricepublish.common.exception.GlobalException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Component
@Aspect
public class FormValidation {

    //注解式表单验证
    @Around("@annotation(com.hywa.pricepublish.config.formValidation.JsonValidate)")
    @Order(1)
    public Object formValidation(ProceedingJoinPoint point) throws Throwable {
        //获得对象方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        JsonValidate annotation = method.getAnnotation(JsonValidate.class);
        //通过反射调用注解中的方法
        Class<?> clazz = Class.forName(annotation.value().getName());
        Method clazzMethod = clazz.getMethod("validate", Object[].class);
        Object invoke =null;
        try {
            invoke = clazzMethod.invoke(clazz.newInstance(), (Object) point.getArgs());
        }catch(GlobalException e){
            e.getCause();
        }
        if (null != invoke) {
            return invoke;
        }
        return point.proceed();
    }
}
