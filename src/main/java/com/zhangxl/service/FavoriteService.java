package com.zhangxl.service;

import com.zhangxl.model.User;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 9:02 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义处理 Favorite 相关业务的接口
 */
public interface FavoriteService {

    /**
     * 根据 传入的 rid 和 user 判断 rid 对应的 Route 是否被 user 收藏
     * <pre>createTime:
     * 4/26/19 9:05 AM</pre>
     *
     * @param strRid
     * @param loginUser
     * @return
     */
    String isFavorite(String strRid, User loginUser);

    /**
     * 根据传入的 strRid 和 loginUser 执行 addFavorite 功能
     * <pre>createTime:
     * 4/26/19 10:17 AM</pre>
     *
     * @param strRid
     * @param loginUser
     * @return
     */
    String addFavorite(String strRid, User loginUser);

    /**
     * 处理分页查询收藏的业务
     * <pre>createTime:
     * 4/26/19 4:04 PM</pre>
     *
     * @param strPageNum
     * @param strPageSize
     * @param loginUser
     * @return
     */
    String queryPage(String strPageNum, String strPageSize, User loginUser);
}
