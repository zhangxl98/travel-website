package com.zhangxl.servlet;

import com.zhangxl.service.RouteService;
import com.zhangxl.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 7:23 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 接收 Route 旅游路线相关的请求
 */
@WebServlet("/route.do")
public class RouteServlet extends BaseServlet {

    /**
     * 初始化 service
     */
    private RouteService routeService = new RouteServiceImpl();

    /**
     * 接收黑马精选路线请求
     * <br>人气旅游、最新旅游、主题旅游
     * <pre>createTime:
     * 4/24/19 7:25 PM</pre>
     *
     * @param req
     * @param resp
     */
    private void routeCareChoose(HttpServletRequest req, HttpServletResponse resp) {

        // 处理数据
        String routeList = routeService.getRouteCareChoose();

        // 响应数据
        try {
            resp.getWriter().println(routeList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收分页查询线路
     * <pre>createTime:
     * 4/25/19 10:16 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void routePageQuery(HttpServletRequest req, HttpServletResponse resp) {

        // 接收请求数据
        String strpageSize = req.getParameter("pageSize");
        String strpageNum = req.getParameter("pageNum");
        String cid = req.getParameter("cid");
        String rname = req.getParameter("rname");

        // 处理数据：调用 Service 层分页查询
        String jsonPageData = routeService.pageQuery(strpageNum, strpageSize, cid, rname);

        // 响应数据
        try {
            resp.getWriter().println(jsonPageData);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收查看线路详情的请求
     * <pre>createTime:
     * 4/25/19 6:26 PM</pre>
     *
     * @param req
     * @param resp
     */
    private void queryRouteDetail(HttpServletRequest req, HttpServletResponse resp) {

        // 接收请求数据
        String rid = req.getParameter("rid");

        // 处理数据：调用 Service 层处理业务
        String jsonRouteDetail = routeService.queryRouteDetailByRid(rid);

        // 响应数据
        try {
            resp.getWriter().println(jsonRouteDetail);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收收藏排行榜查询的请求
     * <pre>createTime:
     * 4/27/19 11:19 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void favoriteRangePageQuery(HttpServletRequest req, HttpServletResponse resp) {

        // 接收请求数据
        String strPageNum = req.getParameter("pageNum");
        String strPageSize = req.getParameter("pageSize");

        // 处理数据：调用 Service 层处理业务
        String jsonPageData = routeService.favoriteRangePageQuery(strPageNum,strPageSize);

        // 响应数据
        try {
            resp.getWriter().println(jsonPageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
