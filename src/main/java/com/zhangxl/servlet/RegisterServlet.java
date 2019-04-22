package com.zhangxl.servlet;

import com.alibaba.fastjson.JSON;
import com.zhangxl.model.User;
import com.zhangxl.service.UserService;
import com.zhangxl.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 3:29 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理注册请求
 */
@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 处理中文乱码
        req.setCharacterEncoding("UTF-8");

        // 接收请求数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 使用 BeanUtils 工具类，把 Map 中的数据封装到实体类中
        User user = new User();
        // 封装结果集的 Map
        Map<String, Object> result = new HashMap<>();
        try {
            BeanUtils.populate(user,parameterMap);

            // 处理数据：把 user 插入到数据库中
            UserService userService = new UserServiceImpl();

            boolean registerFlag = userService.addUser(user);

            result.put("registerFlag",registerFlag);

        } catch (Exception e) {
            e.printStackTrace();

            result.put("registerFlag",false);
            result.put("msg","数据异常，请联系管理员！");
        }

        // 响应数据：json    {name:value} -- Map<key, value>
        String str = JSON.toJSONString(result);
        resp.getWriter().println(str);
    }
}
