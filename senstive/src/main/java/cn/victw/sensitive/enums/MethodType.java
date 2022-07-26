package cn.victw.sensitive.enums;

/**
 * 方法注解接口， 便于外部扩展
 * <p>
 * 子类包含两个属性， key, value
 *
 * @author Vincent Tao
 * @date 2022/7/23 14:42
 */
public enum MethodType {
    /**
     * 数据列表加载
     */
    TABLE_LIST("TABLE_LIST"),

    /**
     * 详情
     */
    DETAIL("DETAIL");

    private final String key;


    MethodType(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
