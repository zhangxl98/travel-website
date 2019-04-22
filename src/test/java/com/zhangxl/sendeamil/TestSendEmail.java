package com.zhangxl.sendeamil;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 7:48 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 测试邮件发送
 */
public class TestSendEmail {

    /**
     * 第一步：引入Apache mail依赖
     *
     *     <!--apache mail-->
     *     <dependency>
     *         <groupId>org.apache.commons</groupId>
     *         <artifactId>commons-email</artifactId>
     *         <version>1.5</version>
     *     </dependency>
     */

    /**
     * 第二步：测试邮件发送
     * <p>
     * 【commons mail开发步骤】
     * 第一步：创建HtmlEmail对象；
     * 第二步：设置邮箱服务器相关参数：
     * 1、服务器地址；
     * 2、授权码；
     * 3、编码集；
     * 第三步：设置收件人与发件人参数；
     * 第四步：设置邮件正文内容；
     * 第五步：发送邮件；
     */

    /**
     * 测试代码
     * 4/22/19 7:57 PM
     */
    @Test
    public void testSendEmail() throws EmailException {

        // 1.创建一个 HtmlEmail 对象
        HtmlEmail htmlEmail1 = new HtmlEmail();

        // 2.设置邮箱服务器的参数
        htmlEmail1.setHostName("smtp.126.com");

        // 设置编码
        htmlEmail1.setCharset("UTF-8");


        // 3.设置邮箱验证（客户端授权）
        // username : 邮箱账号
        // password： 客户端授权码
        htmlEmail1.setAuthentication("ch***rk@126.com","root123456");

        // 4.设置发件人和收件人
        htmlEmail1.setFrom("ch***rk@126.com","server");
        htmlEmail1.addTo("ch***ter@126.com","user");

        // 5.设置主题和正文
        htmlEmail1.setSubject("服务器发送测试");
        htmlEmail1.setTextMsg("Hello World！");

        // 6.发送邮件
        htmlEmail1.send();
    }

    /**
     *
     * 4/22/19 8:40 PM
     */
    @Test
    public void testMailUtil() throws EmailException {
        TestMailUtil.sendEmail();
    }

}
