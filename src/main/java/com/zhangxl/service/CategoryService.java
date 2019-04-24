package com.zhangxl.service;

import com.zhangxl.model.Category;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 10:47 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 处理 Category 相关业务的接口
 */
public interface CategoryService {

    /**
     * 查询所有的商品分类
     * <pre>createTime:
     * 4/24/19 10:55 AM</pre>
     *
     * @return
     */
    List<Category> queryAllCategory();
}
