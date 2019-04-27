package com.zhangxl.dao;

import com.zhangxl.model.Favorite;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据传入的参数进行分页查询
     * <pre>createTime:
     * 4/27/19 8:37 AM</pre>
     *
     * @param uid
     * @param startCount
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> pageQuery(Integer uid, int startCount, int pageSize);

    /**
     * 查询 uid 对应的 user 的 favorite 记录条数
     * <pre>createTime:
     * 4/27/19 10:05 AM</pre>
     *
     * @param uid
     * @return
     */
    int queryTotalCount(Integer uid);
}
