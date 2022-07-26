package cn.victw.demo;

import cn.victw.sensitive.annotation.SensitiveMethod;
import cn.victw.sensitive.service.UseSecretService;
import cn.victw.sensitive.util.BeanUtil;
import cn.victw.sensitive.util.RequestUtil;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Objects;

@Service
public class UseSecretServiceImpl implements UseSecretService {


    @Override
    public Boolean useSecret() {
        System.out.println("自定义实现");
        Method requestMethod = RequestUtil.getRequestMethod(RequestUtil.getRequest());
        SensitiveMethod annotation = requestMethod.getAnnotation(SensitiveMethod.class);
        if (Objects.isNull(annotation)) {
            return false;
        }
        return !annotation.useDes();
    }
}
