package com.zhangxl.model;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 10:56 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 收藏实体类
 */
public class Favorite implements Serializable {

    // 旅游路线对象
    private Route route;
    // 收藏时间
    private String date;
    // 所属用户
    private User user;

    /**
     * 空参构造
     */
    public Favorite() {
    }

    /**
     * 满参构造
     *
     * @param route
     * @param date
     * @param user
     */
    public Favorite(Route route, String date, User user) {
        this.route = route;
        this.date = date;
        this.user = user;
    }


    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "route=" + route +
                ", date='" + date + '\'' +
                ", user=" + user +
                '}';
    }
}
