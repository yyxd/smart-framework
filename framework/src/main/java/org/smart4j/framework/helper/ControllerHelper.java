package org.smart4j.framework.helper;

import org.smart4j.framework.annotaion.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by HinTi on 2019/7/6.
 * Goal: 对于所有定义了Controller注解的类，需要获取该类中带有Action注解的方法，并且获取Action注解中的请求表达式，进而获取请求方法与路径，封装一个请求对象（Request）与处理对象（Handler），将Request 与Handler 建设映射关系，放入ActionMap中
 * ControllerHelper 类
 */
public class ControllerHelper {
    public static final Map<Request, Handler> ACTION_MAP = new HashMap<>();
    static {
        // 获取所有的Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?> controllerClass:controllerClassSet){
                // 获取Controller类的所有函数
                Method[] methods = controllerClass.getDeclaredMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    // 遍历 函数
                    for(Method method:methods){
                        if(method.isAnnotationPresent(Action.class)){
                            // 从Action 注解中获取URL映射规则
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();//获取URL路由
                            // 验证URL映射规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtil.isNotEmpty(array)&& array.length==2){
                                    // 获取请求方法与请求路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    //初始化 ACTION_MAP
                                    ACTION_MAP.put(request,handler);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}