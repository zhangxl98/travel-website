package com.zhangxl.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 5:23 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 生成验证码
 */

@WebServlet("/checkCode.do")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        request.getSession().setAttribute("code",sb.toString());
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
        ImageIO.write(image, "PNG", response.getOutputStream());
    }
}
