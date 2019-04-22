package com.zhangxl.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 10:57 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 商家实体类
 */
public class Seller implements Serializable {

    // 商家 Id
    private Integer sid;
    // 商家名称
    private String sname;
    // 商家电话
    private String consphone;
    // 商家地址
    private String address;

    /**
     * 空参构造
     */
    public Seller() {
    }

    /**
     * 满参构造
     *
     * @param sid
     * @param sname
     * @param consphone
     * @param address
     */
    public Seller(Integer sid, String sname, String consphone, String address) {
        this.sid = sid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", consphone='" + consphone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
