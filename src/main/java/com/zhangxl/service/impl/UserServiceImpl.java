package com.zhangxl.service.impl;

import com.zhangxl.dao.UserDao;
import com.zhangxl.dao.impl.UserDaoImpl;
import com.zhangxl.model.User;
import com.zhangxl.service.UserService;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 2:03 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 User 相关业务的实现类
 */
public class UserServiceImpl implements UserService {

    /**
     * 初始化 dao
     */
    private UserDao userDao = new UserDaoImpl();

    /**
     * email 唯一性校验<br>
     * 数据库中没有查到数据，return true，校验通过
     *
     * @param email
     * @return
     */
    @Override
    public boolean checkEmail(String email) {

        return userDao.queryByEmail(email) == null;
    }

    /**
     * 添加 user <br>
     * 添加成功 -> <code>return true</code>
     *
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {

        return userDao.save(user) != 0;
    }
}
