package cn.victw.demo;

import cn.victw.sensitive.service.DesensitizationService;

/**
 * @author Vincent Tao
 * @date 2022/8/1 11:23
 */
public class CustomDesensitizationService extends DesensitizationService {

    @Override
    public String des(String value) {
        return value + "2";
    }
}
