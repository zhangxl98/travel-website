package com.zhangxl.servlet;

import com.alibaba.fastjson.JSON;
import com.zhangxl.model.Category;
import com.zhangxl.service.CategoryService;
import com.zhangxl.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 10:15 AM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 接收商品分类相关的请求
 */
@WebServlet("/category.do")
public class CategoryServlet extends BaseServlet {

    /**
     * 初始化 service
     */
    CategoryService categoryService = new CategoryServiceImpl();

    /**
     * 接收导航栏展示所有分类的请求
     * <pre>createTime:
     * 4/24/19 10:38 AM</pre>
     *
     * @param req
     * @param resp
     */
    private void queryAll(HttpServletRequest req, HttpServletResponse resp) {

        // 处理数据：调用 Service 查询
        List<Category> categoryList = categoryService.queryAllCategory();

        // 响应数据
        try {
            resp.getWriter().println(JSON.toJSONString(categoryList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
