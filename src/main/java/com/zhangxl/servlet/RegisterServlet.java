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
import java.io.PrintWriter;
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

        // 接收请求数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 封装结果集的 Map
        Map<String, Object> result = new HashMap<>();

        // 处理验证码校验

        // 获取用户输入的验证码
        String[] userCode = parameterMap.get("check");
        //获取服务器生成的验证码
        String serverCode = (String) req.getSession().getAttribute("code");

        //验证码校验
        if (!serverCode.equalsIgnoreCase(String.valueOf(userCode[0]))) {
            //校验不通过：1、看到登录页面；2、页面上需要嵌入错误信息；
            result.put("msg","验证码错误");
        }else {

            // 使用 BeanUtils 工具类，把 Map 中的数据封装到实体类中
            User user = new User();

            try {
                BeanUtils.populate(user, parameterMap);

                // 处理数据：把 user 插入到数据库中
                UserService userService = new UserServiceImpl();

                boolean registerFlag = userService.addUser(user);

                result.put("registerFlag", registerFlag);

            } catch (Exception e) {
                e.printStackTrace();

                result.put("registerFlag", false);
                result.put("msg", "数据异常，请联系管理员！");
            }
        }

        // 响应数据：json    {name:value} -- Map<key, value>
        String str = JSON.toJSONString(result);
        resp.getWriter().println(str);
    }
}
