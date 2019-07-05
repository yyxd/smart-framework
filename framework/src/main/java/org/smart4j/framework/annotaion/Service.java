package org.smart4j.framework.annotaion;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/7/5.
 * Goal: 服务类注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {
}
