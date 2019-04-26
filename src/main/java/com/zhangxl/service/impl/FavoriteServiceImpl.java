package com.zhangxl.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxl.dao.FavoriteDao;
import com.zhangxl.dao.impl.FavoriteDaoImpl;
import com.zhangxl.model.Favorite;
import com.zhangxl.model.User;
import com.zhangxl.service.FavoriteService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 9:03 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 实现 Favorite 相关业务的方法
 */
public class FavoriteServiceImpl implements FavoriteService {

    /**
     * 初始化 Dao
     */
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 根据 传入的 rid 和 user 判断 rid 对应的 Route 是否被 user 收藏
     * <pre>createTime:
     * 4/26/19 9:05 AM</pre>
     *
     * @param strRid
     * @param loginUser
     * @return
     */
    @Override
    public String isFavorite(String strRid, User loginUser) {

        // 创建用于封装结果的 Map
        Map<String, Object> result = new HashMap<>(16);

        // 判断用户是否已登录
        if (null == loginUser) {
            // 用户未登录 ==> isFavorite = false
            result.put("isFavorite", false);
        } else {
            // 用户已登录
            // 获取用户的 id
            Integer uid = loginUser.getUid();
            // 调用 Dao 层，查询获取对应的 Favorite
            Favorite favorite = favoriteDao.queryByRidUid(strRid, uid);


            /*
              null != favorite ==> 用户已收藏 ==> isFavorite = true

              Date: 4/26/19 9:18 AM
            */
            result.put("isFavorite", (null != favorite));
        }

        // 将结果集转换为 JSON 并返回
        return JSON.toJSONString(result);
    }
}
