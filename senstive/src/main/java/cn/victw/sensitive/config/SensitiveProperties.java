package cn.victw.sensitive.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 读取配置文件
 *
 * @author Vincent Tao
 * @date 2022/7/23 13:53
 */
@SuppressWarnings("serial")
@ConfigurationProperties(prefix = "sensitive")
public class SensitiveProperties implements Serializable {

    /**
     * 是否启用当前功能
     */
    private Boolean enable;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
