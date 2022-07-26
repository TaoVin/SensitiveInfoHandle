package cn.victw.sensitive.config;

import cn.victw.sensitive.service.UseSecretService;
import cn.victw.sensitive.service.impl.UseSecretServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置自动装配类
 *
 * @author Vincent Tao
 * @date 2022/7/23 14:05
 */
@Configuration
@ComponentScan("cn.victw")
@EnableConfigurationProperties(SensitiveProperties.class)
@ConditionalOnProperty(prefix = "sensitive", value = "enable", matchIfMissing = true)
public class SensitiveAutoConfiguration {


}
