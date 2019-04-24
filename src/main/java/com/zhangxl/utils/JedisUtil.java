package com.zhangxl.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 11:38 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description Jedis 工具类
 */
public class JedisUtil {

    private JedisUtil() {
    }


    private static JedisPool jedilPool;
    private static int maxtotal;
    private static int maxwaitmillis;
    private static String host;
    private static int port;
    ;

    /**
     * 读取 jedis.properties 配置文件
     */
    static {
        ResourceBundle rb = ResourceBundle.getBundle("jedis");
        maxtotal = Integer.parseInt(rb.getString("maxtotal"));
        maxwaitmillis = Integer.parseInt(rb.getString("maxwaitmillis"));
        host = rb.getString("host");
        port = Integer.parseInt(rb.getString("port"));
    }

    /**
     * 创建连接池
     */
    static {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxtotal);
        jedisPoolConfig.setMaxWaitMillis(maxwaitmillis);
        jedilPool = new JedisPool(jedisPoolConfig, host, port);
    }

    /**
     * 获取 Jedis
     */
    public static Jedis getJedis() {
        return jedilPool.getResource();
    }

    /**
     * 关闭 Jedis
     */
    public static void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
