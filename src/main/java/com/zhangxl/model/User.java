package com.zhangxl.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 10:35 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 用户实体类
 */
public class User implements Serializable {

    // 用户 Id
    private Integer uid;
    // 用户名，账号
    private String username;
    // 密码
    private String password;
    // 真实姓名
    private String name;
    // 出生日期
    private String birthday;
    // 性别
    private String sex;
    // 电话号码
    private String telephone;
    // 邮箱账号
    private String email;
    // 激活码
    private String code;
    // 激活状态
    private Integer status;

    /**
     * 空参构造
     */
    public User() {
    }

    /**
     * 满参构造
     *
     * @param uid
     * @param username
     * @param password
     * @param name
     * @param birthday
     * @param sex
     * @param telephone
     * @param email
     * @param code
     * @param status
     */
    public User(Integer uid, String username, String password, String name, String birthday, String sex, String telephone, String email, String code, Integer status) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.telephone = telephone;
        this.email = email;
        this.code = code;
        this.status = status;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", status=" + status +
                '}';
    }
}
