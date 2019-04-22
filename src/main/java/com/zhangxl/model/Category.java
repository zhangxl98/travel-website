package com.zhangxl.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 10:53 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 分类实体类
 */
public class Category implements Serializable {

    // 分类 Id
    private Integer cid;
    // 分类名称
    private String cname;

    /**
     * 空参构造
     */
    public Category() {
    }

    /**
     * 满参构造
     *
     * @param cid
     * @param cname
     */
    public Category(Integer cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
