package com.zhangxl.servlet;

import com.zhangxl.service.UserService;
import com.zhangxl.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 1:52 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 接收 email 唯一性校验请求
 */
@WebServlet("/checkEmail.do")
public class CheckEmailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 接收请求数据
        String email = req.getParameter("email");

        // 处理数据
        UserService userService = new UserServiceImpl();

        // 如果 checkFlag == true -> 校验通过，数据库中没有这条数据
        boolean checkFlag = userService.checkEmail(email);

        // 响应数据
        resp.getWriter().println(checkFlag);
    }
}
