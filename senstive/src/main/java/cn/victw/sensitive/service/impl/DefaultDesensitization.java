package cn.victw.sensitive.service.impl;

import cn.victw.sensitive.service.DesensitizationService;

/**
 * @author Vincent Tao
 * @date 2022/8/1 11:33
 */
public class DefaultDesensitization extends DesensitizationService {
    @Override
    public String des(String value) {
        return value;
    }
}
