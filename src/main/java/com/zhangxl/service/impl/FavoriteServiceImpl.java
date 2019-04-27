package com.zhangxl.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxl.dao.FavoriteDao;
import com.zhangxl.dao.RouteDao;
import com.zhangxl.dao.impl.FavoriteDaoImpl;
import com.zhangxl.dao.impl.RouteDaoImpl;
import com.zhangxl.model.Favorite;
import com.zhangxl.model.User;
import com.zhangxl.service.FavoriteService;
import com.zhangxl.utils.C3p0Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 9:03 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 实现 Favorite 相关业务的方法
 */
public class FavoriteServiceImpl implements FavoriteService {

    /**
     * 初始化 Dao
     */
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private RouteDao routeDao = new RouteDaoImpl();

    /**
     * 根据 传入的 rid 和 user 判断 rid 对应的 Route 是否被 user 收藏
     * <pre>createTime:
     * 4/26/19 9:05 AM</pre>
     *
     * @param strRid
     * @param loginUser
     * @return
     */
    @Override
    public String isFavorite(String strRid, User loginUser) {

        // 创建用于封装结果的 Map
        Map<String, Object> result = new HashMap<>(16);

        // 判断用户是否已登录
        if (null == loginUser) {
            // 用户未登录 ==> isFavorite = false
            result.put("isFavorite", false);
        } else {
            // 用户已登录
            // 获取用户的 id
            Integer uid = loginUser.getUid();
            // 调用 Dao 层，查询获取对应的 Favorite
            Favorite favorite = favoriteDao.queryByRidUid(strRid, uid);


            /*
              null != favorite ==> 用户已收藏 ==> isFavorite = true

              Date: 4/26/19 9:18 AM
            */
            result.put("isFavorite", (null != favorite));
        }

        // 将结果集转换为 JSON 并返回
        return JSON.toJSONString(result);
    }

    /**
     * 调用 Dao 存储 strRid 和 loginUser 对应的 Favorite
     * <pre>createTime:
     * 4/26/19 10:18 AM</pre>
     *
     * @param strRid
     * @param loginUser
     * @return
     */
    @Override
    public String addFavorite(String strRid, User loginUser) {

        // 获取 DataSource
        DataSource dataSource = C3p0Util.getDataSource();
        // 实例化 JdbcTemplate ==> 使用 JdbcTemplate 事务控制
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        // 启动事务管理器（获取 dataSource 数据库连接对象绑定到当前线程中
        TransactionSynchronizationManager.initSynchronization();
        // 从数据源中获取 jdbcTemplate 操作的当前连接对象
        Connection connection = DataSourceUtils.getConnection(dataSource);


        // 创建用于封装结果的 Map
        Map<String, Object> result = new HashMap<>(16);

        /*
          判断用户是否已登录
          用户已登陆 ==> null != loginUser ==> loginFlag = true

          Date: 4/26/19 10:29 AM
        */
        result.put("loginFlag", (null != loginUser));

        // 如果用户已登录，进行添加收藏业务
        if (null != loginUser) {
            if (StringUtils.isNotBlank(strRid)) {
                // strRid 不为 空串、空白符、null
                Integer rid = Integer.valueOf(strRid);


                // JdbcTemplate 事务控制

                try {
                    // 关闭自动提交
                    connection.setAutoCommit(false);

                    /*
                      调用 Dao 层进行数据持久化
                      如果都成功 ==> addFavoriteFlag = true
                      事务回滚 ==> addFavoriteFlag = false

                      Date: 4/26/19 10:50 AM
                    */
                    int saveFavoriteNum = favoriteDao.save(rid, loginUser.getUid());

                    // 更新 count（收藏数量）
                    routeDao.updateCountByRid(rid);

                    // 提交事务
                    connection.commit();

                    // 添加返回结果
                    result.put("addFavoriteFlag", true);
                } catch (SQLException e) {
                    // 出现异常，回滚
                    try {
                        connection.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    // 添加返回结果
                    result.put("addFavoriteFlag", false);
                } finally {
                    try {
                        // 释放资源
                        // 释放当前线程与连接对象的绑定
                        TransactionSynchronizationManager.clearSynchronization();
                        // 重置为自动提交事务
                        connection.setAutoCommit(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                // 调用 RouteDao 获取最新的收藏数量
                result.put("routeCount", routeDao.queryCountByRid(rid));
            }
        }

        /*
          result：
                loginFlag :         用户登录状态 true ==> 已登录
                addFavoriteFlag :   用户添加收藏状态 true ==> 添加成功
                routeCount :       最新的收藏数量

          Date: 4/26/19 10:51 AM
        */

        // 将结果集转换为 JSON 并返回
        return JSON.toJSONString(result);
    }

    /**
     * 处理分页查询收藏的业务
     * <pre>createTime:
     * 4/26/19 4:05 PM</pre>
     *
     * @param strPageNum
     * @param strPageSize
     * @param loginUser
     * @return
     */
    @Override
    public String queryPage(String strPageNum, String strPageSize, User loginUser) {

        // 创建用于封装结果的 Map
        Map<String, Object> result = new HashMap<>(16);

         /*
          判断用户是否已登录
          用户已登陆 ==> null != loginUser ==> loginFlag = true

          Date: 4/26/19 10:29 AM
        */
        result.put("loginFlag", (null != loginUser));

        // 用户已登录，进行数据分页
        if (null != loginUser) {

            /**
             * 初始化 pageNum、pageSize
             */
            int pageNum = 1;
            int pageSize = 12;

            // 判断传入的数据是否为空串、空白符、null
            if (StringUtils.isNotBlank(strPageNum)) {
                pageNum = Integer.valueOf(strPageNum);
            }
            if (StringUtils.isNotBlank(strPageSize)) {
                pageSize = Integer.valueOf(strPageSize);
            }


            /*
              计算起始记录（数据库从哪条开始查）
              LIMIT startCount,pageSize

              Date: 4/27/19 8:23 AM
            */
            int startCount = (pageNum - 1) * pageSize;

            // 调用 Dao 层，获取分页数据
            List<Map<String,Object>> pageData = favoriteDao.pageQuery(loginUser.getUid(),startCount,pageSize);


            // 封装数据
            result.put("pageData",pageData);
        }


        // 将结果集转换为 JSON 并返回
        return JSON.toJSONString(result);
    }
}
