package org.meco.common.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangYang
 * @date 2020/12/22
 */
@Component
@ConfigurationProperties(prefix = WhitelistPathConfig.PREFIX)
public class WhitelistPathConfig {

    static final String PREFIX = "ignore";

    /**
     * 白名单 Url
     */
    private List<String> urls = new ArrayList<>();

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
        urls.add("/swagger-ui/**");
        urls.add("/actuator/**");
        urls.add("/**/v2/api-docs");
        urls.add("/v2/api-docs");
    }
}
