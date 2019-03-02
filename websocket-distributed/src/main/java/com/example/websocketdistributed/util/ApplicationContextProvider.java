package com.example.websocketdistributed.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring上下文对象信息
 * 静态类方法:
 * 1)返回spring上下文对象: applicationContext
 * 2)通过spring上下文对象获取指定对象的实例信息, 包括3个重载的方法实现
 *
 * @author xujunqi
 * @date 2017/11/10 14:04
 */
@Component
public class ApplicationContextProvider
        implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     *
     * @author xujunqi
     * @date: 2017/11/16 19:28
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean
     *
     * @author: xujunqi
     * @date: 2017/11/16 19:28
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     *
     * @author: xujunqi
     * @date: 2017/11/16 19:28
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name, 以及Clazz返回指定的Bean
     *
     * @author: xujunqi
     * @date: 2017/11/16 19:28
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
