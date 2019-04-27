package com.zhangxl.servlet;

import com.alibaba.fastjson.JSON;
import com.zhangxl.model.User;
import com.zhangxl.service.FavoriteService;
import com.zhangxl.service.impl.FavoriteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 8:26 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 接收 favorite（收藏）相关的请求
 */
@WebServlet("/favorite.do")
public class FavoriteServlet extends BaseServlet {

    /**
     * 初始化 Service
     */
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 接收判断是否已被收藏的请求
     * <pre>createTime:
     * 4/26/19 8:28 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void isFavorite(HttpServletRequest req, HttpServletResponse resp) {

        // 1.接收请求数据
        String strRid = req.getParameter("rid");


        // 2.处理数据

        // 获取当前登录的用户
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        // 调用 Service 层判断用户是否收藏该线路
        String jsonFavorite = favoriteService.isFavorite(strRid, loginUser);


        // 3.响应数据
        try {
            resp.getWriter().println(jsonFavorite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收用户添加收藏的请求
     * <pre>createTime:
     * 4/26/19 10:05 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void addFavorite(HttpServletRequest req, HttpServletResponse resp) {

        // 接收请求数据
        String strRid = req.getParameter("rid");

        // 处理数据
        // 获取当前登录的用户
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        /*
          调用 Service 层处理业务，判断是否收藏成功
          添加成功 ==> addFavoriteFlag = true, RouteCount = Route.count
          添加失败 ==> addFavoriteFlag = false, RouteCount = Route.count

          Date: 4/26/19 10:09 AM
        */
        String jsonAddFavorite = favoriteService.addFavorite(strRid, loginUser);

        // 响应数据
        try {
            resp.getWriter().println(jsonAddFavorite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收查询收藏分页的请求
     * <pre>createTime:
     * 4/26/19 3:57 PM</pre>
     *
     * @param req
     * @param resp
     */
    private void favoriteQueryPage(HttpServletRequest req, HttpServletResponse resp) {

        // 接收请求数据
        String strPageNum = req.getParameter("pageNum");
        String strPageSize = req.getParameter("pageSize");

        User loginUser = (User) req.getSession().getAttribute("loginUser");

        // 处理数据：调用 Service 层处理分页查询业务
        String jsonPageData = favoriteService.queryPage(strPageNum, strPageSize, loginUser);

        // 响应数据
        try {
            resp.getWriter().println(jsonPageData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
