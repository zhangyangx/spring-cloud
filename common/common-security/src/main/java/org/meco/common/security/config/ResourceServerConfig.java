package org.meco.common.security.config;

import lombok.AllArgsConstructor;
import org.meco.common.security.custom.ResourceAuthExceptionEntryPoint;
import org.meco.common.security.handler.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * 资源服务器
 *
 * @author ZhangYang
 * @date 2020/12/22
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final TokenStore tokenStore;

    private final ResourceAuthExceptionEntryPoint resourceAuthExceptionEntryPoint;

    /**
     * 资源id=应用名称
     */
    @Value("${spring.application.name}")
    private final String resourceId;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore)
                .resourceId(resourceId)
                // 配置拒绝访问处理器
                .stateless(true).accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(resourceAuthExceptionEntryPoint);
    }

    /**
     * 请求配置
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 设置HTTP请求访问权限，避免feign调用失败
                .antMatchers("/**").permitAll()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
