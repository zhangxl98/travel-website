package com.zhangxl.dao;

import com.zhangxl.model.Favorite;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/26/19 9:20 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义 Favorite 相关的 CRUD 操作的接口
 */
public interface FavoriteDao {

    /**
     * 根据传入的 strRid 和 uid 查询出对应的 Favorite
     * <pre>createTime:
     * 4/26/19 9:22 AM</pre>
     *
     * @param strRid
     * @param uid
     * @return
     */
    Favorite queryByRidUid(String strRid, Integer uid);

    /**
     * 对传入的 rid 和 uid 进行数据持久化
     * <pre>createTime:
     * 4/26/19 10:40 AM</pre>
     *
     * @param rid
     * @param uid
     * @return
     */
    int save(Integer rid, Integer uid);
}
