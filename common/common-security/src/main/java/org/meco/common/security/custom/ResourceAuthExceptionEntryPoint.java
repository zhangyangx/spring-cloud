package org.meco.common.security.custom;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * AuthenticationEntryPoint  是Spring Security Web一个概念模型接口，顾名思义，他所建模的概念是:“认证入口点”。
 * 它在用户请求处理过程中遇到认证异常时，被ExceptionTranslationFilter用于开启特定认证方案(authentication schema)的认证流程。
 * 当用户请求了一个受保护的资源，但是用户没有通过认证，那么抛出异常，AuthenticationEntryPoint. Commence(..)就会被调用。
 * 这个对应的代码在ExceptionTranslationFilter中，当ExceptionTranslationFilter catch到异常后，就会间接调用AuthenticationEntryPoint。
 *
 * @author ZhangYang
 * @date 2020/12/22
 */
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int code = 401;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        if (authException != null) {
            jsonObject.put("msg", authException.getMessage());
        }
        response.setStatus(code);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(jsonObject.toJSONString());
    }
}
