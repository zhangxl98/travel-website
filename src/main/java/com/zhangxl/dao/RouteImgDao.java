package com.zhangxl.dao;

import com.zhangxl.model.RouteImg;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/25/19 8:05 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义旅游线路线图片 RouteImg 的 CRUD 接口
 */
public interface RouteImgDao {

    /**
     * 根据 rid 查询对应的信息
     * <pre>createTime:
     * 4/25/19 8:07 PM</pre>
     *
     * @param rid
     * @return
     */
    List<RouteImg> queryByRid(String rid);
}
