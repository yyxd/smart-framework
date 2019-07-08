package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HinTi on 2019/7/6.
 * Goal: 返回视图对象
 * 从Handler 对象中获取Action的返回值，该返回值可能有两种情况
 * 1. View类型的试图对象，返回一个JSP页面
 * 2. Data 类型的数据对象，返回一个JSON 数据
 */
public class View {
    // 视图路径
    private String path;

    // 模型数据
    private Map<String,Object> model;

    public View(String path){
        this.path = path;
        model = new HashMap<>();
    }
    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}