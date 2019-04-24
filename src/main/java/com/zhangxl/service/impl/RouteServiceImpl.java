package com.zhangxl.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxl.dao.RouteDao;
import com.zhangxl.dao.impl.RouteDaoImpl;
import com.zhangxl.model.Route;
import com.zhangxl.service.RouteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 7:45 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理旅游路线相关业务的实现类
 */
public class RouteServiceImpl implements RouteService {

    /**
     * 初始化 dao
     */
    private RouteDao routeDao = new RouteDaoImpl();

    /**
     * 处理黑马精选路线业务
     * <br>人气旅游、最新旅游、主题旅游
     * <pre>createTime:
     * 4/24/19 7:46 PM</pre>
     *
     * @return
     */
    @Override
    public String getRouteCareChoose() {

        // 获取人气旅游路线
        List<Route> populateRouteList = routeDao.queryOrderByCount();
        // 获取最新旅游路线
        List<Route> newestRouteList = routeDao.queryOrderByRdate();
        // 获取主题旅游路线
        List<Route> themeRouteList = routeDao.queryByIsThemeTour();

        // 对获取到的结果使用 Map 进行封装
        Map<String, List<Route>> result = new HashMap<>();
        result.put("populateRouteList", populateRouteList);
        result.put("newestRouteList", newestRouteList);
        result.put("themeRouteList", themeRouteList);

        // 转换为 JSON 字符串并返回
        return JSON.toJSONString(result);
    }
}
