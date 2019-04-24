package com.zhangxl.service.impl;

import com.zhangxl.dao.CategoryDaao;
import com.zhangxl.dao.impl.CategoryDaoImpl;
import com.zhangxl.model.Category;
import com.zhangxl.service.CategoryService;

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
    public List<Category> queryAllCategory() {

        return categoryDaao.queryAll();
    }
}
