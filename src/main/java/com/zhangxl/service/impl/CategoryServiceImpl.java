package com.zhangxl.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxl.dao.CategoryDaao;
import com.zhangxl.dao.impl.CategoryDaoImpl;
import com.zhangxl.model.Category;
import com.zhangxl.service.CategoryService;
import com.zhangxl.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 10:48 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 Category 相关业务的实现类
 */
public class CategoryServiceImpl implements CategoryService {

    /**
     * 初始化 Dao
     */
    CategoryDaao categoryDaao = new CategoryDaoImpl();

    /**
     * 调用 dao 查询所有的商品分类
     * <pre>createTime:
     * 4/24/19 10:55 AM</pre>
     *
     * @return
     */
    @Override
    public String queryAllCategory() {

        // 添加缓存业务

        // 优先从缓存中查： redis
        Jedis jedis = JedisUtil.getJedis();
        String categoryJson = jedis.get("TRAVEL_WEBSITE_CATEGORY_LIST");

        if (null == categoryJson) {
            // 缓存中没有查到数据，调用 Dao 从数据库中查询
            List<Category> categoryList = categoryDaao.queryAll();

            // 把数据库中查出的数据转成 JSON
            categoryJson = JSON.toJSONString(categoryList);
            // 存储到 Redis 缓存中
            jedis.set("TRAVEL_WEBSITE_CATEGORY_LIST", categoryJson);
        }

        // 释放资源
        JedisUtil.close(jedis);

        // 返回数据
        return categoryJson;
    }
}
