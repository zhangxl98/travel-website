package com.zhangxl.utils;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.security.MessageDigest;

import static org.junit.Assert.*;

/**
 * Md5Util Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 22, 2019</pre>
 */
public class Md5UtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: encodeByMd5(String str)
     */
    @Test
    public void testEncodeByMd5() throws Exception {
//TODO: Test goes here...

        String str = "123456";


        // Java中MessageDigest类封装了MD5和SHA算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 调用 md5 算法，即返回 16 个 byte 类型的值
        byte[] byteArray = md5.digest(str.getBytes());


        for (byte b : byteArray) {
            System.out.println("b = " + b);
        }

    }


} 
