package org.meco.common.job.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.meco.common.job.props.XxlJobExecutorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author ZhangYang
 * @date 2020/12/21
 */
@AllArgsConstructor
@Slf4j
public class XxlJobConfig {

    private final XxlJobExecutorProperties xxlJobExecutorProperties;

    private final ConfigurableEnvironment configurableEnvironment;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(configurableEnvironment.getProperty("xxl.job.admin.addresses"));
        xxlJobSpringExecutor.setAppname(xxlJobExecutorProperties.getAppName());
        xxlJobSpringExecutor.setAddress(xxlJobExecutorProperties.getAddress());
        xxlJobSpringExecutor.setIp(xxlJobExecutorProperties.getIp());
        xxlJobSpringExecutor.setPort(xxlJobExecutorProperties.getPort());
        xxlJobSpringExecutor.setAccessToken(configurableEnvironment.getProperty("xxl.job.accessToken"));
        xxlJobSpringExecutor.setLogPath(xxlJobExecutorProperties.getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobExecutorProperties.getLogRetentionDays());
        return xxlJobSpringExecutor;
    }

    /**
     * 针对多网卡、容器内部署等情况，可借助 "spring-cloud-commons" 提供的 "InetUtils" 组件灵活定制注册IP；
     *
     *      1、引入依赖：
     *          <dependency>
     *             <groupId>org.springframework.cloud</groupId>
     *             <artifactId>spring-cloud-commons</artifactId>
     *             <version>${version}</version>
     *         </dependency>
     *
     *      2、配置文件，或者容器启动变量
     *          spring.cloud.inetutils.preferred-networks: 'xxx.xxx.xxx.'
     *
     *      3、获取IP
     *          String ip_ = inetUtils.findFirstNonLoopbackHostInfo().getIpAddress();
     */

}