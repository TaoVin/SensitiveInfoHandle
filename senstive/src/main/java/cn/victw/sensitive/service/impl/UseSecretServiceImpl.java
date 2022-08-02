package cn.victw.sensitive.service.impl;

import cn.victw.sensitive.annotation.SensitiveMethod;
import cn.victw.sensitive.service.UseSecretService;
import cn.victw.sensitive.util.RequestUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 判断是否需要脱敏默认实现
 *
 * @author Vincent Tao
 * @date 2022/7/25 21:53
 */
public class UseSecretServiceImpl implements UseSecretService {

    @Override
    public Boolean useSecret() {
        System.out.println("默认实现true");
        HttpServletRequest request = RequestUtil.getRequest();
        Method requestMethod = RequestUtil.getRequestMethod(request);
        if (Objects.isNull(requestMethod)) {
            return true;
        }

        SensitiveMethod annotation = requestMethod.getAnnotation(SensitiveMethod.class);
        if (Objects.isNull(annotation)) {
            return true;
        }
        return annotation.useDes();
    }
}
