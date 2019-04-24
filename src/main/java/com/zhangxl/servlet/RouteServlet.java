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
}
