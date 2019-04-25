package com.zhangxl.dao.impl;

import com.zhangxl.dao.RouteDao;
import com.zhangxl.model.Route;
import com.zhangxl.utils.C3p0Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 8:50 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 查询旅游线路 Route 的 CRUD 实现类
 */
public class RouteDaoImpl implements RouteDao {

    /**
     * 初始化 JdbcTemplate
     */
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    /**
     * 查询人气旅游线路
     * <br>按照 count 字段（收藏量）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 8:51 PM</pre>
     *
     * @return
     */
    @Override
    public List<Route> queryOrderByCount() {

        String sql = "SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' ORDER BY count DESC LIMIT 0,4";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }

    /**
     * 查询最新旅游线路
     * <br>按照 rdate 字段（上架时间）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 9:00 PM</pre>
     *
     * @return
     */
    @Override
    public List<Route> queryOrderByRdate() {

        String sql = "SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' ORDER BY rdate DESC LIMIT 0,4";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }

    /**
     * 查询主题旅游线路
     * <br>按照 isThemeTour 字段（0->不是，1->是）进行筛选，并按按照 rdate 字段（上架时间）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 9:05 PM</pre>
     *
     * @return
     */
    @Override
    public List<Route> queryByIsThemeTour() {

        String sql = "SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' AND isThemeTour='1' ORDER BY rdate DESC LIMIT 0,4";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));
    }

    /**
     * 根据传入的 startCount 和 pageSize 进行分页查询
     * <pre>createTime:
     * 4/25/19 11:02 AM</pre>
     *
     * @param startCount
     * @param pageSize
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public List<Route> pageQuery(int startCount, int pageSize, String cid, String rname) {

        // 动态 SQL 拼接
        StringBuilder sql = new StringBuilder("SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' ");

        // 定义参数集合
        List<Object> paramList = new ArrayList<>();

        // cid 不为 空串、空白符、null
        if (StringUtils.isNotBlank(cid)) {
            // 动态拼接
            sql.append(" AND cid=? ");
            // 加入参数
            paramList.add(cid);
        }

        // rname 不为 空串、空白符、null
        if (StringUtils.isNotBlank(rname)) {
            // 动态拼接
            sql.append(" AND rname LIKE? ");
            // 加入参数 -- 模糊匹配
            paramList.add("%" + rname + "%");
        }

        // 拼接分页条件
        sql.append(" LIMIT ?,? ");
        // 加入分页条件参数
        paramList.add(startCount);
        paramList.add(pageSize);

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Route>(Route.class), paramList.toArray());
    }

    /**
     * 查询 tab_route 表中的记录有效条数
     * <pre>createTime:
     * 4/25/19 12:48 PM</pre>
     *
     * @param cid
     * @param rname
     * @return
     */
    @Override
    public int queryTotalCount(String cid, String rname) {

        // 创建动态 SQL
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM tab_route WHERE rflag='1' ");

        // 定义参数集合
        List<Object> paramList = new ArrayList<>();

        // cid 不为 空串、空白符、null
        if (StringUtils.isNotBlank(cid)) {
            // 动态拼接
            sql.append(" AND cid=? ");
            // 加入参数
            paramList.add(cid);
        }

        // rname 不为 空串、空白符、null
        if (StringUtils.isNotBlank(rname)) {
            // 动态拼接
            sql.append(" AND rname LIKE ? ");
            // 加入参数 -- 模糊匹配
            paramList.add("%" + rname + "%");
        }

        return jdbcTemplate.queryForObject(sql.toString(), Integer.class, paramList.toArray());
    }
}
