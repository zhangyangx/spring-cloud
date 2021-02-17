package org.meco.common.job.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>XxlJob配置属性</p>
 *
 * @author ZhangYang
 * @date 2020/12/21
 */
@Configuration
@ConfigurationProperties(prefix = XxlJobExecutorProperties.PREFIX)
@Data
public class XxlJobExecutorProperties {

    final static String PREFIX = "xxl.job.executor";

    private String appName;
    private String address;
    private String ip;
    private int port;
    private String logPath;
    private int logRetentionDays;
}
