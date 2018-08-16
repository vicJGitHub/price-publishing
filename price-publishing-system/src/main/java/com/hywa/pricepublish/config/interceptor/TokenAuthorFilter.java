package com.hywa.pricepublish.config.interceptor;

import static com.hywa.pricepublish.common.enums.CommonEnum.RE_LOGIN;

import com.alibaba.fastjson.JSON;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.representation.ResponseBase;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@WebFilter(urlPatterns = {"/*"}, filterName = "tokenAuthorFilter") //过滤规则
public class TokenAuthorFilter implements Filter {

    @Autowired
    private TokenManager tokenManager;

    /* 这里设置不被拦截的请求路径 */
    private static final List<String> unFilterUrlList = Arrays
            .asList("/user/login", "/user/pcLogin",
                    "user/checkToken", "user/pcCheckToken",
                    "/dataOrigin","/myCrawl","zip","code");

    /* 判断请求路径是否为不拦截的请求路径 */
    private boolean isNotFilter(String url) {
        for (String s : unFilterUrlList) {
            if (url.contains(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        //设置允许跨域的配置
        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
        rep.setHeader("Access-Control-Allow-Origin", "*");
        // 允许的访问方法
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
        rep.setHeader("Access-Control-Max-Age", "3600");
        rep.setHeader("Access-Control-Allow-Headers",
                "token,Origin, X-Requested-With, Content-Type, Accept, accessToken");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");

        String accessToken = req.getHeader("accessToken");
        ResponseBase resultInfo = new ResponseBase();

        boolean isFilter = false;

        String method = ((HttpServletRequest) request).getMethod();
        if (method.equals("OPTIONS")) {
            rep.setStatus(HttpServletResponse.SC_OK);
        } else {
            if (isNotFilter(req.getRequestURL().toString())) {
                isFilter = true;
            } else if (StringUtils.isEmpty(accessToken)) {
                resultInfo.setRetHead(RE_LOGIN.getIndex(), RE_LOGIN.getValue());
            } else {
                log.info(accessToken);
                //验证token
                TokenModel model = tokenManager.getToken(accessToken);
                if (tokenManager.checkToken(model)) {
                    isFilter = true;
                } else {
                    resultInfo.setRetHead(RE_LOGIN.getIndex(), RE_LOGIN.getValue());
                }
            }

            if (isFilter) {
                log.info("token filter过滤ok!");
                chain.doFilter(request, response);
            } else {// 验证失败
                PrintWriter writer = null;
                OutputStreamWriter osw = null;
                try {
                    osw = new OutputStreamWriter(response.getOutputStream(),
                            "UTF-8");
                    writer = new PrintWriter(osw, true);
                    String jsonStr = JSON.toJSONString(resultInfo);
                    writer.write(jsonStr);
                    writer.flush();
                    writer.close();
                    osw.close();
                } catch (IOException e) {
                    log.error("过滤器返回信息失败:" + e.getMessage(), e);
                } finally {
                    if (null != writer) {
                        writer.close();
                    }
                    if (null != osw) {
                        osw.close();
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {
    }
}
