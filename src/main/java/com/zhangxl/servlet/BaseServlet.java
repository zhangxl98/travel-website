package com.zhangxl.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 6:47 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 分发请求
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("op");

        // 根据 URL 后的参数调用方法，使用反射的方式
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            // 动态调用方法

            // 取消权限检查
            method.setAccessible(true);

            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
