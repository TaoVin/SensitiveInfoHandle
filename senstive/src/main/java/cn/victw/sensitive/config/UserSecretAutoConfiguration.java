package cn.victw.sensitive.config;

import cn.victw.sensitive.service.UseSecretService;
import cn.victw.sensitive.service.impl.UseSecretServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置自动装配类
 *
 * @author Vincent Tao
 * @date 2022/7/23 14:05
 */
@Configuration
public class UserSecretAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(UseSecretService.class)
    public UseSecretService useSecretService() {
        return new UseSecretServiceImpl();
    }

}
