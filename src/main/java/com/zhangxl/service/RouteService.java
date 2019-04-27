package com.zhangxl.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 7:39 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义旅游路线相关业务的接口
 */
public interface RouteService {

    /**
     * 处理黑马精选路线业务
     * <br>人气旅游、最新旅游、主题旅游
     * <pre>createTime:
     * 4/24/19 7:44 PM</pre>
     *
     * @return
     */
    String getRouteCareChoose();

    /**
     * 处理分页查询线路业务
     * <pre>createTime:
     * 4/25/19 10:22 AM</pre>
     *
     * @param strpageNum
     * @param strpageSize
     * @param cid
     * @param rname
     * @return
     */
    String pageQuery(String strpageNum, String strpageSize, String cid, String rname);

    /**
     * 处理获取 RouteDetail（线路详情） 的业务
     * <pre>createTime:
     * 4/25/19 6:38 PM</pre>
     *
     * @param rid
     * @return
     */
    String queryRouteDetailByRid(String rid);

    /**
     * 处理分页查询收藏排行榜的业务
     * <pre>createTime:
     * 4/27/19 11:26 AM</pre>
     *
     * @param strPageNum
     * @param strPageSize
     * @return
     */
    String favoriteRangePageQuery(String strPageNum, String strPageSize);
}
