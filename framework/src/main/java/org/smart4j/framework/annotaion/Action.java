package org.smart4j.framework.annotaion;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: Action 注解
 * 使用时类似于
 * @Action("get:/customer)
 * A:/B A代表请求类型，B代表请求路径
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    // 请求路径
    String value();
}
