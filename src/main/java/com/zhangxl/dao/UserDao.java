package com.zhangxl.dao;

import com.zhangxl.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 2:12 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 User 相关的 CRUD 操作的接口
 */
public interface UserDao {

    /**
     * 通过 email 查询 user
     *
     * @param email
     * @return
     */
    User queryByEmail(String email);

    /**
     * 保存 user 到数据库 <br>
     * 返回数据库影响条数
     *
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 根据　code 更新　user status
     *
     * @param code
     * @return
     */
    int updateStatusByCode(String code);
}
