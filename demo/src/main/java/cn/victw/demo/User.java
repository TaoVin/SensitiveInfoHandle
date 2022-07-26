package cn.victw.demo;

import cn.victw.sensitive.annotation.Sensitive;
import cn.victw.sensitive.rules.DesensitizationType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 人员信息，测试实体
 *
 * @author Vincent Tao
 * @date 2022/7/25 19:51
 */
public class User {

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 用户名
     */
    @Sensitive(des = DesensitizationType.USER_NAME)
    private String userName;


    /**
     * 手机号
     */
    @Sensitive(des = DesensitizationType.PHONE)
    private String phone;


    /**
     * 证件号
     */
    @Sensitive(des = DesensitizationType.ACCOUNT)
    private String certNo;


    public User(Long id, String userName, String phone, String certNo) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.certNo = certNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }
}
