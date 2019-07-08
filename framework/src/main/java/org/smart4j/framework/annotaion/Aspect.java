package org.smart4j.framework.annotaion;

import java.lang.annotation.*;

/**
 * Created by HinTi on 2019/7/8.
 * Goal: 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Aspect {
    /**
     *注解
     */
    Class<? extends Annotation> value();
}
