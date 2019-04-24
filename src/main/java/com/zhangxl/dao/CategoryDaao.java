package com.zhangxl.dao;

import com.zhangxl.model.Category;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 10:59 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义 Category 的 CRUD 的接口
 */
public interface CategoryDaao {

    /**
     * 查询所有的 Category
     * <pre>createTime:
     * 4/24/19 11:00 AM</pre>
     *
     * @return
     */
    List<Category> queryAll();
}
