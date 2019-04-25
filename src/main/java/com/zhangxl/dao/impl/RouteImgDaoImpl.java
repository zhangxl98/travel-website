package com.zhangxl.dao.impl;

import com.zhangxl.dao.RouteImgDao;
import com.zhangxl.model.RouteImg;
import com.zhangxl.utils.C3p0Util;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/25/19 8:06 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 实现旅游线路线图片 RouteImg 的 CRUD 方法
 */
public class RouteImgDaoImpl implements RouteImgDao {

    /**
     * 初始化 JdbcTemplate
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    /**
     * 根据 rid 查询信息
     * <pre>createTime:
     * 4/25/19 8:08 PM</pre>
     *
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> queryByRid(String rid) {

        String sql = "SELECT rgid, rid, bigPic, smallPic FROM tab_route_img WHERE rid=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }
}
