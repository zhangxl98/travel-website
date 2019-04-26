package com.zhangxl.dao.impl;

import com.zhangxl.dao.FavoriteDao;
import com.zhangxl.model.Favorite;
import com.zhangxl.utils.C3p0Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 9:20 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 实现 Favorite 相关的 CRUD 操作的方法
 */
public class FavoriteDaoImpl implements FavoriteDao {

    /**
     * 初始化 JdbcTemplate
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    /**
     * 根据传入的 strRid 和 uid 查询出对应的 Favorite
     * <pre>createTime:
     * 4/26/19 9:22 AM</pre>
     *
     * @param strRid
     * @param uid
     * @return
     */
    @Override
    public Favorite queryByRidUid(String strRid, Integer uid) {

        if (StringUtils.isNotBlank(strRid)) {
            // strRid 不为 空串、空白符、null
            Integer rid = Integer.valueOf(strRid);

            // 从数据库中查询
            String sql = "SELECT rid, date, uid FROM tab_favorite WHERE rid=? AND uid=?";
            try {
                return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
            } catch (DataAccessException e) {
                return null;
            }
        }
        return null;
    }
}
