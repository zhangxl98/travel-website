package com.zhangxl.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 11:01 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 旅游路线图片实体类
 */
public class RouteImg implements Serializable {

    // 商品图片 Id
    private Integer rgid;
    // 旅游商品 Id
    private Integer rid;
    // 详情商品大图
    private String bigPic;
    // 详情商品小图
    private String smallPic;

    /**
     * 空参构造
     */
    public RouteImg() {
    }

    /**
     * 满参构造
     *
     * @param rgid
     * @param rid
     * @param bigPic
     * @param smallPic
     */
    public RouteImg(Integer rgid, Integer rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    @Override
    public String toString() {
        return "RouteImg{" +
                "rgid=" + rgid +
                ", rid=" + rid +
                ", bigPic='" + bigPic + '\'' +
                ", smallPic='" + smallPic + '\'' +
                '}';
    }
}
