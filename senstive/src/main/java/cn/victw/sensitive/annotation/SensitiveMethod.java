package cn.victw.sensitive.annotation;

import cn.victw.sensitive.enums.MethodType;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Vincent Tao
 * @date 2022/7/20 9:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@JacksonAnnotationsInside
public @interface SensitiveMethod {

    /**
     * 是否生效
     *
     * @return
     */
    boolean useDes() default true;

    /**
     * 方法类型
     *
     * @return
     */
     String type() default "TABLE_LIST";
}
