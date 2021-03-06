package org.smart4j.framework;

import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * Created by HinTi on 2019/7/6.
 * Goal: 初始化框架，入口程序加载helper类
 */
public class HelperLoader {
    public static void init(){
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class,
                AopHelper.class
        };
        for(Class<?> cls: classList){
            ClassUtil.loadClass(cls.getName(),false);
        }
    }
}