package com.zhangxl.dao.impl;

import com.zhangxl.model.User;
import com.zhangxl.utils.C3p0Util;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.Assert.*;

/**
 * UserDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 22, 2019</pre>
 */
public class UserDaoImplTest {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryByEmail(String email)
     */
    @Test
    public void testQueryByEmail() throws Exception {
//TODO: Test goes here...

        String email = "zs@zs.com";

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
        User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);

        System.out.println("user = " + user);

    }


} 
