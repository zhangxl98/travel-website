package com.zhangxl.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 8:51 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 发送邮件的工具类
 */
public class MailUtil {
    private static String host;
    private static String charset;
    private static String username;
    private static String password;


    /**
     * 静态代码块加载配置信息
     */
    static {

        ResourceBundle bundle = ResourceBundle.getBundle("mail");
        host = bundle.getString("mail.host");
        charset = bundle.getString("mail.charset");
        username = bundle.getString("mail.username");
        password = bundle.getString("mail.password");
    }

    /**
     * 发送邮件
     *
     * @param eamilTo
     * @param msg
     * @throws EmailException
     */
    public static void sendEmail(String eamilTo, String msg) throws EmailException {

        // 1.创建一个 HtmlEmail 对象
        HtmlEmail htmlEmail = new HtmlEmail();

        // 2.设置邮箱服务器的参数
        htmlEmail.setHostName(host);

        // 设置编码
        htmlEmail.setCharset(charset);


        // 3.设置邮箱验证（客户端授权）
        // username : 邮箱账号
        // password： 客户端授权码
        htmlEmail.setAuthentication(username, password);

        // 4.设置发件人和收件人
        htmlEmail.setFrom(username, "【官方服务器】");
        htmlEmail.addTo(eamilTo, "【注册用户】");

        // 5.设置主题和正文
        htmlEmail.setSubject("【注册激活邮件】");
        // 网易邮箱
        htmlEmail.setTextMsg(msg);
        // qq 邮箱
//        htmlEmail.setHtmlMsg(msg);

        // 6.发送邮件
        htmlEmail.send();
    }
}
