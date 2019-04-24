package com.zhangxl.dao.impl;

import com.zhangxl.dao.CategoryDaao;
import com.zhangxl.model.Category;
import com.zhangxl.utils.C3p0Util;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 11:00 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description Category 的 CRUD 的实现类
 */
public class CategoryDaoImpl implements CategoryDaao {

    /**
     * 初始化 JdbcTemplate
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    /**
     * 查询所有的 Category
     * <pre>createTime:
     * 4/24/19 11:01 AM</pre>
     *
     * @return
     */
    @Override
    public List<Category> queryAll() {

        String sql = "SELECT cid, cname FROM tab_category ";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
    }
}
