package com.zhangxl.dao.impl;

import com.zhangxl.dao.UserDao;
import com.zhangxl.model.User;
import com.zhangxl.utils.C3p0Util;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 2:14 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 User 相关的 CRUD 操作的实现类
 */
public class UserDaoImpl implements UserDao {

    /**
     * 初始化 JDBCTemplate
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    /**
     * 通过 email 查询 user
     *
     * @param email
     * @return
     */
    @Override
    public User queryByEmail(String email) {

        String sql = "SELECT uid, " +
                "       username, " +
                "       password, " +
                "       name, " +
                "       birthday, " +
                "       sex, " +
                "       telephone, " +
                "       email, " +
                "       status, " +
                "       code " +
                "FROM tab_user " +
                "WHERE email = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 保存 user 到数据库 <br>
     * 返回数据库影响条数
     *
     * @param user
     * @return
     */
    @Override
    public int save(User user) {

        String sql = "INSERT INTO tab_user (username, password, name, birthday, sex, telephone, email, status, code) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据　code 更新　status=1
     *
     * @param code
     * @return
     */
    @Override
    public int updateStatusByCode(String code) {

        String sql = "UPDATE tab_user " +
                "SET status=1 " +
                "WHERE code = ? " +
                "  AND status = 0";
        return jdbcTemplate.update(sql, code);
    }
}
