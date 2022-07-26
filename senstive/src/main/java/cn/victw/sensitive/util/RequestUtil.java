package cn.victw.sensitive.util;

import ch.qos.logback.classic.jmx.MBeanUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 获取请求信息工具类
 *
 * @author Vincent Tao
 * @date 2022/7/23 19:39
 */
public class RequestUtil {



    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        try {
            return getRequestAttributes().getRequest();
        } catch (Exception e) {
            return null;
        }
    }


    public static Method getRequestMethod(HttpServletRequest request) {
        RequestMappingHandlerMapping handlerMapping = BeanUtil.getBean(RequestMappingHandlerMapping.class);
        try {
            HandlerExecutionChain handlerChain = handlerMapping.getHandler(request);
            if (Objects.isNull(handlerChain)) {
                return null;
            }
            //通过处理链找到对应的HandlerMethod类
            HandlerMethod handler = (HandlerMethod) handlerChain.getHandler();
            //HandlerMethod中有bean和method
            //处理请求的方法
            return handler.getMethod();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
