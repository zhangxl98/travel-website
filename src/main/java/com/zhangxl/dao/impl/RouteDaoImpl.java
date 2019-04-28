package com.zhangxl.dao.impl;

import com.zhangxl.dao.RouteDao;
import com.zhangxl.model.Route;
import com.zhangxl.utils.C3p0Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public int queryTotalCountByCidRname(String cid, String rname) {

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
            paramList.add("%" + rname.trim() + "%");
        }

        try {
            return jdbcTemplate.queryForObject(sql.toString(), Integer.class, paramList.toArray());
        } catch (DataAccessException e) {
            return 0;
        }
    }

    /**
     * 根据 rid 查出 Route 的所有相关信息
     * <pre>createTime:
     * 4/25/19 7:00 PM</pre>
     *
     * @param rid
     * @return
     */
    @Override
    public Map<String, Object> queryRouteDetailByRid(String rid) {

        String sql = "SELECT * FROM tab_route tbr INNER JOIN tab_category tbc ON tbr.cid=tbc.cid INNER JOIN tab_seller tbs ON tbr.sid=tbs.sid WHERE tbr.rid=?";
        try {
            return jdbcTemplate.queryForMap(sql, rid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * 根据 rid 查询 count（收藏数量）
     * <pre>createTime:
     * 4/26/19 11:13 AM</pre>
     *
     * @param rid
     * @return
     */
    @Override
    public int queryCountByRid(Integer rid) {

        String sql = "SELECT count FROM tab_route WHERE rid=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 更新 count（收藏量）count++
     * <pre>createTime:
     * 4/26/19 11:47 AM</pre>
     *
     * @param rid
     */
    @Override
    public void updateCountByRid(Integer rid) {

        String sql = "UPDATE tab_route SET count=count+1 WHERE rid=?";
        jdbcTemplate.update(sql, rid);
    }

    /**
     * 分页查询 Route 按照 Count 降序
     * <pre>createTime:
     * 4/27/19 12:28 PM</pre>
     *
     * @param startCount
     * @param pageSize
     * @param paraMap
     * @return
     */
    @Override
    public List<Route> pageQueryOrderByCount(int startCount, int pageSize, Map<String, String> paraMap) {

        // 取出参数
        String rname = paraMap.get("rname");
        String startPrice = paraMap.get("startPrice");
        String endPrice = paraMap.get("endPrice");


        // 动态拼接 SQL
        StringBuilder sql = new StringBuilder("SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' ");

        // 定义参数集合
        ArrayList<Object> paramList = new ArrayList<>();

        // 判空
        if (StringUtils.isNotBlank(rname)) {
            sql.append(" AND rname like ? ");
            paramList.add("%" + rname.trim() + "%");
        }
        if (StringUtils.isNotBlank(startPrice)) {
            sql.append(" AND price >= ? ");
            paramList.add(startPrice);
        }
        if (StringUtils.isNotBlank(endPrice)) {
            sql.append(" AND price <= ? ");
            paramList.add(endPrice);
        }


        sql.append(" ORDER BY count DESC LIMIT ?,? ");
        paramList.add(startCount);
        paramList.add(pageSize);

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<Route>(Route.class), paramList.toArray());
    }

    /**
     * 按条件查询符合的记录条数
     * <pre>createTime:
     * 4/27/19 12:44 PM</pre>
     *
     * @param paraMap
     * @return
     */
    @Override
    public int queryTotalCount(Map<String, String> paraMap) {

        // 取出参数
        String rname = paraMap.get("rname");
        String startPrice = paraMap.get("startPrice");
        String endPrice = paraMap.get("endPrice");

        // 动态拼接 SQL
        StringBuilder sql = new StringBuilder("SELECT count(*) FROM tab_route WHERE rflag='1' ");

        // 定义参数集合
        ArrayList<Object> paramList = new ArrayList<>();

        // 判空
        if (StringUtils.isNotBlank(rname)) {
            sql.append(" AND rname like ? ");
            paramList.add("%" + rname.trim() + "%");
        }
        if (StringUtils.isNotBlank(startPrice)) {
            sql.append(" AND price >= ? ");
            paramList.add(startPrice);
        }
        if (StringUtils.isNotBlank(endPrice)) {
            sql.append(" AND price <= ? ");
            paramList.add(endPrice);
        }


        sql.append(" ORDER BY count DESC ");

        try {
            return jdbcTemplate.queryForObject(sql.toString(), Integer.class, paramList.toArray());
        } catch (Exception e) {
            return 0;
        }
    }
}
