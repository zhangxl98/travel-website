package com.zhangxl.servlet;

import com.alibaba.fastjson.JSON;
import com.zhangxl.model.User;
import com.zhangxl.service.UserService;
import com.zhangxl.service.impl.UserServiceImpl;
import com.zhangxl.utils.MailUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 6:55 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 接收 user 相关的请求
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 接收 email 唯一性校验请求
     *
     * @param req
     * @param resp
     */
    private void checkEmail(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求数据
        String email = req.getParameter("email");

        // 处理数据

        // 如果 checkFlag == true -> 校验通过，数据库中没有这条数据
        boolean checkFlag = userService.checkEmail(email);

        // 响应数据
        try {
            resp.getWriter().println(checkFlag);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收注册请求
     *
     * @param req
     * @param resp
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 封装结果集的 Map
        Map<String, Object> result = new HashMap<>();

        // 处理验证码校验

        // 获取用户输入的验证码
        String[] userCheckCode = parameterMap.get("check");
        //获取服务器生成的验证码
        String serverCheckCode = (String) req.getSession().getAttribute("code");

        //验证码校验
        if (!serverCheckCode.equalsIgnoreCase(String.valueOf(userCheckCode[0]))) {
            //校验不通过：1、看到登录页面；2、页面上需要嵌入错误信息；
            result.put("msg", "验证码错误");
        } else {

            // 使用 BeanUtils 工具类，把 Map 中的数据封装到实体类中
            User user = new User();

            try {
                BeanUtils.populate(user, parameterMap);

                // 处理数据：把 user 插入到数据库中
                boolean registerFlag = userService.addUser(user);


                // 注册成功，给用户发送激活邮件
                if (registerFlag) {
                    // 发送邮件
                    MailUtil.sendEmail(user.getEmail(),
                            "<h1>恭喜您，注册成功！</h1>" +
                                    "<a href='http://localhost:8080/user.do?op=active&code=" +
                                    user.getCode() +
                                    "'>请点击此链接，进行激活</a>");
                }

                // 在控制台打印出激活地址
                System.out.println("active URL ===> http://localhost:8080/userServlet?op=active&code=" + user.getCode());

                result.put("registerFlag", registerFlag);

            } catch (Exception e) {
                e.printStackTrace();

                result.put("registerFlag", false);
                result.put("msg", "数据异常，请联系管理员！");
            }
        }

        // 响应数据：json    {name:value} -- Map<key, value>
        String str = JSON.toJSONString(result);
        try {
            resp.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收用户激活请求
     *
     * @param req
     * @param resp
     */
    private void active(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求数据
        String code = req.getParameter("code");

        // 处理数据

        // 如果 activeFlag == true -> 激活成功
        boolean activeFlag = userService.active(code);

        // 响应数据
        if (activeFlag) {
            // 成功 -> 跳转到登录页面
            try {
                resp.getWriter().println("激活成功，3秒钟之后跳转到登录页面！");
                resp.setHeader("refresh", "3;/login.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // 失败 -> 跳转到注册页面
            try {
                resp.getWriter().println("激活失败，请联系管理员，3秒钟之后跳转到注册页面！");
                resp.setHeader("refresh", "3;/register.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 接收用户登录请求
     *
     * @param req
     * @param resp
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) {
        // 接收请求数据
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 处理数据

        // 封装数据的结果集
        Map<String, Object> result = new HashMap<>();

        // 验证码校验

        // 获取用户输入的验证码
        String[] userCheckCode = parameterMap.get("check");
        // 获取服务器产生的验证码
        String serverCheckCode = (String) req.getSession().getAttribute("code");

        // 进行对比
        if (!serverCheckCode.equalsIgnoreCase(String.valueOf(userCheckCode[0]))) {
            // 验证码校验不通过，给出提示信息
            result.put("loginFlag", false);
            result.put("msg", "验证码错误");

        } else {
            // 验证码校验通过
            // 用来封装的对象
            User user = new User();

            try {
                // 使用 BeanUtils 封装
                BeanUtils.populate(user, parameterMap);

                User realUser = userService.login(user);

                if (realUser == null) {
                    // 用户名或密码错误，登录失败

                    result.put("loginFlag", false);
                    result.put("msg", "用户名或密码错误");
                } else {

                    // 用户信息校验通过

                    if (realUser.getStatus() == 0) {
                        // 该用户未激活
                        result.put("loginFlag", false);
                        result.put("msg", "账号未激活，请查看激活邮件");
                    } else {
                        // 登录成功 -> 存储用户到 Session 中
                        req.getSession().setAttribute("loginUser", realUser);
                        result.put("loginFlag", true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.put("loginFlag", false);
                result.put("msg", "数据异常，请联系管理员");
            }
        }

        // 响应数据 JSON
        String str = JSON.toJSONString(result);
        try {
            resp.getWriter().println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接收用户注销登录的请求
     * <pre>createTime:
     * 4/24/19 9:40 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void logOut(HttpServletRequest req, HttpServletResponse resp) {

        // 收到请求后，直接销毁该用户的 Session
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        if (null != loginUser) {
            // 如果是登录状态（loginUser 不为空），销毁
            req.getSession().invalidate();
        }

        // 响应数据，重定向到登录页面
        try {
            resp.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收获取登录用户信息的请求
     * <pre>createTime:
     * 4/23/19 10:49 PM</pre>
     *
     * @param req
     * @param resp
     */
    private void getLoginUser(HttpServletRequest req, HttpServletResponse resp) {

        // 1.从域中取得数据
        User loginUser = (User) req.getSession().getAttribute("loginUser");

        // 2.处理数据
        // 封装的结果集
        Map<String, Object> result = new HashMap<>();
        if (null != loginUser) {
            // 用户已登录
            result.put("loginFlag", true);
            result.put("loginUserName", loginUser.getName());
        } else {
            // 用户未登录
            result.put("loginFlag", false);
            result.put("msg", "用户未登录");
        }

        // 3.响应数据 ==> JSON
        try {
            resp.getWriter().println(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成验证码
     *
     * @param req
     * @param resp
     */
    private void checkCode(HttpServletRequest req, HttpServletResponse resp) {
        // gui 生成图片
        // 1 高和宽
        int height = 30;
        int width = 80;
//        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
        String data = "A";
        Random random = new Random();
        // 2 创建一个图片
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 3 获得画板
        Graphics g = image.getGraphics();
        // 4 填充一个矩形
        // * 设置颜色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(1, 1, width - 2, height - 2);
        // * 设置字体
        g.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 25));
        StringBuffer sb = new StringBuffer();
        // 5 写随机字
        for (int i = 0; i < 4; i++) {
            // 设置颜色--随机数
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));

            // 获得随机字
            int index = random.nextInt(data.length());
            String str = data.substring(index, index + 1);
            // 写入
            g.drawString(str, width / 6 * (i + 1), 20);
            sb.append(str);//  获取验证码数据
        }
        //  验证码保存到session中
        req.getSession().setAttribute("code", sb.toString());
        // 6 干扰线
        for (int i = 0; i < 3; i++) {
            // 设置颜色--随机数
            g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            // 随机绘制先
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
            // 随机点
            g.drawOval(random.nextInt(width), random.nextInt(height), 2, 2);
        }

        // end 将图片响应给浏览器
        ImageIO.setUseCache(false);
        try {
            ImageIO.write(image, "PNG", resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
