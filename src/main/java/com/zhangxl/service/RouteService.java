package com.zhangxl.service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author zhangxl98
 * @Date 4/24/19 7:39 PM
 * @OS Ubuntu 18.04 LTS
 * @Device DELL-Inspiron-15-7559
 * @Modified By
 * @Version V1.0.0
 * @Description 定义旅游路线相关业务的接口
 */
public interface RouteService {

    /**
     * 处理黑马精选路线业务
     * <br>人气旅游、最新旅游、主题旅游
     * <pre>createTime:
     * 4/24/19 7:44 PM</pre>
     *
     * @return
     */
    String getRouteCareChoose();
}
