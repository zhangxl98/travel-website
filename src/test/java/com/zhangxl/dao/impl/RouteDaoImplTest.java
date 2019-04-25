package com.zhangxl.dao.impl;

import com.zhangxl.model.Route;
import com.zhangxl.utils.C3p0Util;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * RouteDaoImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 25, 2019</pre>
 */
public class RouteDaoImplTest {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(C3p0Util.getDataSource());

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: queryOrderByCount()
     */
    @Test
    public void testQueryOrderByCount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryOrderByRdate()
     */
    @Test
    public void testQueryOrderByRdate() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryByIsThemeTour()
     */
    @Test
    public void testQueryByIsThemeTour() throws Exception {
//TODO: Test goes here...

        String sql = "SELECT rid, rname, price, routeIntroduce, rflag, rdate, isThemeTour, count, cid, rimage, sid, sourceId FROM tab_route WHERE rflag='1' AND isThemeTour='1' ORDER BY rdate DESC LIMIT 0,4";


        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class));

        System.out.println("routeList = " + routeList);

        System.out.println("--------------------");

//        Map<String, Object> routeMap = jdbcTemplate.queryForMap(sql);
//
//        System.out.println("routeMap = " + routeMap);
    }

    /**
     * Method: pageQuery(int startCount, int pageSize, String cid, String rname)
     */
    @Test
    public void testPageQuery() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryTotalCount(String cid, String rname)
     */
    @Test
    public void testQueryTotalCount() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryRouteDetailByRid(String rid)
     */
    @Test
    public void testQueryRouteDetailByRid() throws Exception {
//TODO: Test goes here...

        String rid = "1";

        String sql = "SELECT * FROM tab_route tbr INNER JOIN tab_category tbc ON tbr.cid=tbc.cid INNER JOIN tab_seller tbs ON tbr.sid=tbs.sid WHERE tbr.rid=?";

        Map<String, Object> resultMap = jdbcTemplate.queryForMap(sql, rid);

        System.out.println("resultMap = " + resultMap);

        System.out.println("-----------------------");

        List<Object> resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Object>(Object.class), rid);

        System.out.println("resultList = " + resultList);
    }


} 
