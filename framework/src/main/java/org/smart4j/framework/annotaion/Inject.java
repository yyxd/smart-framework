package org.smart4j.framework.annotaion;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: 依赖注入注解 用于注解字段
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inject {
}
