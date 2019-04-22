package com.zhangxl.service;

import com.zhangxl.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 2:02 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 User 相关业务的接口
 */
public interface UserService {

    /**
     * email 唯一性校验  <br>
     * 数据库中没有查到数据，return true，校验通过
     *
     * @param email
     * @return
     */
    boolean checkEmail(String email);

    /**
     * 添加 user <br>
     * 添加成功 -> <code>return true</code>
     *
     * @param user
     * @return
     */
    boolean addUser(User user);
}
