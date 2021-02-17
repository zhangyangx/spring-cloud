package org.meco.common.security.handler;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 拒绝访问处理器
 * <p>
 *
 * @author ZhangYang
 * @date 2020/12/22
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", HttpStatus.FORBIDDEN.value());
        jsonObject.put("msg", "无权访问");
        byte[] bytes = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(bytes);
    }
}
