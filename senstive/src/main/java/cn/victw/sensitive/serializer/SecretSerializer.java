package cn.victw.sensitive.serializer;

import cn.victw.sensitive.annotation.Sensitive;
import cn.victw.sensitive.rules.DesensitizationType;
import cn.victw.sensitive.service.UseSecretService;
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

import java.io.IOException;
import java.util.Objects;

/**
 * 数据脱敏序列化
 *
 * @author Vincent Tao
 * @date 2022/7/20 9:19
 */
@Component
public class SecretSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizationType desensitizationType;

    @Autowired
    private UseSecretService useSecretService;


    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(desensitizationType.desensitizer().apply(s));
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        if (Objects.nonNull(useSecretService) && !useSecretService.useSecret()) {
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }

        Sensitive annotation = beanProperty.getAnnotation(Sensitive.class);
        if (Objects.nonNull(annotation) && Objects.equals(String.class, beanProperty.getType().getRawClass())) {
            this.desensitizationType = annotation.des();
            return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
}
