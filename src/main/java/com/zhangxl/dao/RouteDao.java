package com.zhangxl.dao;

import com.zhangxl.model.Route;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 8:41 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义旅游线路线 Route 的 CRUD 接口
 */
public interface RouteDao {

    /**
     * 查询人气旅游线路
     * <br>按照 count 字段（收藏量）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 8:48 PM</pre>
     *
     * @return
     */
    List<Route> queryOrderByCount();

    /**
     * 查询最新旅游线路
     * <br>按照 rdate 字段（上架时间）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 8:59 PM</pre>
     *
     * @return
     */
    List<Route> queryOrderByRdate();

    /**
     * 查询主题旅游线路
     * <br>按照 isThemeTour 字段（0->不是，1->是）进行筛选，并按按照 rdate 字段（上架时间）降序排序取 4 条记录
     * <pre>createTime:
     * 4/24/19 9:04 PM</pre>
     *
     * @return
     */
    List<Route> queryByIsThemeTour();

    /**
     * 根据传入的 startCount 和 pageSize 进行分页查询
     * <pre>createTime:
     * 4/25/19 11:01 AM</pre>
     *
     * @param startCount
     * @param pageSize
     * @param cid
     * @param rname
     * @return
     */
    List<Route> pageQuery(int startCount, int pageSize, String cid, String rname);

    /**
     * 查询 tab_route 表中的记录有效条数
     * <pre>createTime:
     * 4/25/19 12:46 PM</pre>
     *
     * @param cid
     * @param rname
     * @return
     */
    int queryTotalCountByCidRname(String cid, String rname);

    /**
     * 根据 rid 查询 Route 的详情
     * <pre>createTime:
     * 4/25/19 6:58 PM</pre>
     *
     * @param rid
     * @return
     */
    Map<String, Object> queryRouteDetailByRid(String rid);

    /**
     * 根据 rid 查询 count（收藏数量）
     * <pre>createTime:
     * 4/26/19 11:13 AM</pre>
     *
     * @param rid
     * @return
     */
    int queryCountByRid(Integer rid);

    /**
     * 更新 count（收藏量）count++
     * <pre>createTime:
     * 4/26/19 11:47 AM</pre>
     *
     * @param rid
     */
    void updateCountByRid(Integer rid);

    /**
     * 分页查询 Route 按照 Count 降序
     * <pre>createTime:
     * 4/27/19 12:27 PM</pre>
     *
     * @param startCount
     * @param pageSize
     * @param paraMap
     * @return
     */
    List<Route> pageQueryOrderByCount(int startCount, int pageSize, Map<String, String> paraMap);

    /**
     * 按条件查询符合的记录条数
     * <pre>createTime:
     * 4/27/19 12:44 PM</pre>
     *
     * @param paraMap
     * @return
     */
    int queryTotalCount(Map<String, String> paraMap);
}
