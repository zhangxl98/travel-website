package com.zhangxl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 4:11 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 将明文数据转成 MD5 加密后的数据
 */
public class Md5Util {

    // 十六进制中的每一个数值
    private static String[] HEX = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public Md5Util() {
    }

    /**
     * 将 String 进行 MD5 加密
     *
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encodeByMd5(String str) throws NoSuchAlgorithmException {
        // Java中MessageDigest类封装了MD5和SHA算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 调用 md5 算法，即返回 16 个 byte 类型的值
        byte[] byteArray = md5.digest(str.getBytes());

        // 注意：MessageDigest 只能将 String 转成 byte[]
        return byteArrayToHexString(byteArray);
    }

    /**
     * 将 byte[] 转成十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteArrayToHexString(byte[] byteArray) {

        StringBuffer sb = new StringBuffer();

        // 遍历 byteArray
        for (byte b : byteArray) {
            // 将每一个 b 转成十六进制字符串
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }

    /**
     * 将 byte 转成十六进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        //将byte类型赋给int类型
        int n = b;
        //如果n是负数
        if (n < 0) {
            //转正数
            //-31的16进制数，等价于求225的16进制数
            n = 256 + n;
        }
        //商(14)，数组的下标
        int d1 = n / 16;
        //余(1)，数组的下标
        int d2 = n % 16;
        //通过下标取值
        return HEX[d1] + HEX[d2];
    }
}
