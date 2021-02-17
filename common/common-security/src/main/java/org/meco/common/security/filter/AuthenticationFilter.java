package org.meco.common.security.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meco.common.security.config.WhitelistPathConfig;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>微服务认证过滤器，验证token</p>
 * <p>用户信息存入上下文</p>
 *
 * @author ZhangYang
 * @date 2020/12/22
 */
@Component
@AllArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private final TokenStore tokenStore;

//    private final IUserClient iSystemUserService;

    private final WhitelistPathConfig whitelistPathConfig;

    @SuppressWarnings("all")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        // 是白名单直接放行
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String allowUrl : whitelistPathConfig.getUrls()) {
            if (pathMatcher.match(allowUrl, path)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // 检查token令牌是否正确.
        OAuth2AccessToken oAuth2AccessToken;
        try {
            oAuth2AccessToken = tokenStore.readAccessToken(request.getHeader("Authorization"));
            Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();
            // 取出用户名称
            String principal = additionalInformation.get("user_name").toString();
            // 远程调用获取用户角色信息 todo
//            List<SystemRole> roleList = iSystemUserService.queryUserRolesByUserId(principal).getData();
//
//            // 远程调用获取用户详细信息
//            SystemUser systemUser = iSystemUserService.userByUsername(principal).getData();
//            String[] authorities = roleList.stream().map(SystemRole::getRoleCode).toArray(String[]::new);

            // 身份信息、权限信息填充到用户身份token对象中
//            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null,
//                    AuthorityUtils.createAuthorityList(authorities));
//            // 创建details
//            authenticationToken.setDetails(systemUser);
            // 将authenticationToken填充到安全上下文
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", HttpStatus.FORBIDDEN.value());
            jsonObject.put("msg", "Permission denied,Please authorize");
            byte[] bytes = jsonObject.toString().getBytes(StandardCharsets.UTF_8);
            response.getOutputStream().write(bytes);
            log.error(e.getMessage(), e);
        }
    }
}
