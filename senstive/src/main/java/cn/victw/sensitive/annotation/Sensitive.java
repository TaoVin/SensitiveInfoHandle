package cn.victw.sensitive.annotation;


import cn.victw.sensitive.rules.DesensitizationType;
import cn.victw.sensitive.serializer.SecretSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vincent Tao
 * @date 2022/7/20 9:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = SecretSerializer.class)
public @interface Sensitive {

    DesensitizationType des();
}
