package com.zhangxl.utils;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/22/19 4:04 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 用于生成 uuid 随机字符串
 */
public class UuidUtil {

    public UuidUtil() {
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
