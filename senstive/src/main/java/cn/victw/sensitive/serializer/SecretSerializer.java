package cn.victw.sensitive.serializer;

import cn.victw.sensitive.annotation.Sensitive;
import cn.victw.sensitive.config.SensitiveProperties;
import cn.victw.sensitive.rules.DesensitizationType;
import cn.victw.sensitive.service.DesensitizationService;
import cn.victw.sensitive.service.UseSecretService;
import cn.victw.sensitive.service.impl.DefaultDesensitization;
import cn.victw.sensitive.service.impl.UseSecretServiceImpl;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 数据脱敏序列化
 *
 * @author Vincent Tao
 * @date 2022/7/20 9:19
 */
@Component
public class SecretSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Sensitive sensitive;

    @Resource
    private UseSecretService useSecretService;

    @Autowired
    private SensitiveProperties sensitiveProperties;

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!Objects.equals(sensitive.des(), DesensitizationType.OTHER)) {
            jsonGenerator.writeString(sensitive.des().desensitizer().apply(s));
        } else {
            Class<?> customDesClass = sensitive.customDes();
            if (!Objects.equals(customDesClass, DefaultDesensitization.class)) {
                try {
                    Object o = customDesClass.getDeclaredConstructor().newInstance();
                    Method des = customDesClass.getDeclaredMethod("des", String.class);
                    Object invoke = des.invoke(o, s);
                    if (null != invoke) {
                        jsonGenerator.writeString(String.valueOf(invoke));
                    } else {
                        jsonGenerator.writeString(s);
                    }
                } catch (Exception e) {
                    jsonGenerator.writeString(s);
                }
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (Objects.isNull(sensitiveProperties) || !sensitiveProperties.getEnable()) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        Sensitive annotation = beanProperty.getAnnotation(Sensitive.class);
        if (Objects.isNull(annotation)
                || (Objects.equals(annotation.des(), DesensitizationType.OTHER)
                && Objects.equals(annotation.customDes(), DefaultDesensitization.class))) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        // 自定义判断是否使用脱敏, 法方返回true 或 false,
        if (Objects.nonNull(useSecretService) && !useSecretService.useSecret()) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        if (Objects.equals(String.class, beanProperty.getType().getRawClass())) {
            this.sensitive = annotation;
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
}
