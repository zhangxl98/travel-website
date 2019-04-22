package com.zhangxl.sendeamil;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 8:30 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 发送邮件的工具类
 */
public class TestMailUtil {

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

    public static void sendEmail() throws EmailException {

        // 1.创建一个 HtmlEmail 对象
        HtmlEmail htmlEmail1 = new HtmlEmail();

        // 2.设置邮箱服务器的参数
        htmlEmail1.setHostName(host);

        // 设置编码
        htmlEmail1.setCharset(charset);


        // 3.设置邮箱验证（客户端授权）
        // username : 邮箱账号
        // password： 客户端授权码
        htmlEmail1.setAuthentication(username,password);

        // 4.设置发件人和收件人
        htmlEmail1.setFrom(username,"server");
        htmlEmail1.addTo("ch***ter@126.com","user");

        // 5.设置主题和正文
        htmlEmail1.setSubject("服务器发送测试3");
        htmlEmail1.setTextMsg("Hello World！");

        // 6.发送邮件
        htmlEmail1.send();
    }
}
