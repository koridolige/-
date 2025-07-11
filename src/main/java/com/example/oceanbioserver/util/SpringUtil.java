package com.example.oceanbioserver.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
* 这段代码是一个 Spring 工具类 SpringUtil，用于获取 ApplicationContext 和获取 Bean 实例。
* 该类实现了 ApplicationContextAware 接口，
* 并通过 setApplicationContext() 方法来获取 ApplicationContext 对象并保存在静态变量中。
提供了以下几个静态方法：
1. getApplicationContext()：获取保存的 ApplicationContext 对象。
2. getBean(String name)：通过传入 Bean 的名称来获取对应的 Bean 实例。
3. getBean(Class<T> clazz)：通过传入 Bean 的类型来获取对应的 Bean 实例。
4. getBean(String name, Class<T> clazz)：通过传入 Bean 的名称和类型来获取对应的 Bean 实例。
在该类上使用了 @Component 注解，表示它会被 Spring 扫描并纳入到 Spring 容器中，
* 并使用 @Autowired 注解将 ApplicationContext 自动注入到该类中。
通过调用这些静态方法，可以方便地在任何地方获取到 ApplicationContext 和需要的 Bean 实例。
* 这对于在 Spring 应用程序中动态获取 Bean 实例非常有用。
*
* 这是一个 Spring 工具类，用于获取 ApplicationContext 和获取 Bean 实例。
* 首先，该类实现了 `ApplicationContextAware` 接口，
* 通过实现 `setApplicationContext()` 方法来获取 ApplicationContext 对象，
* 并将其保存在静态变量 `applicationContext` 中。
* 然后，提供了几个静态方法来方便获取 ApplicationContext 和 Bean 实例：
1. `getApplicationContext()` 方法用于获取保存的 ApplicationContext 对象。
2. `getBean(String name)` 方法通过传入 Bean 的名称（首字母小写）来获取对应的 Bean 实例。
3. `getBean(Class<T> clazz)` 方法通过传入 Bean 的类型来获取对应的 Bean 实例。
4. `getBean(String name, Class<T> clazz)` 方法通过传入 Bean 的名称和类型来获取对应的 Bean 实例。
* 通过调用这些静态方法，可以方便地在任何地方获取到 ApplicationContext 和需要的 Bean 实例。
* 需要注意的是，这里使用了 Spring 中的 `@Component` 注解将该类标记为一个组件，
* 表示它会被 Spring 扫描并纳入到 Spring 容器中。另外，使用了 `@Autowired` 注解将 ApplicationContext 自动注入到该类中。
* */
@Component
public class SpringUtil implements ApplicationContextAware {
    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取 Bean.
     * @param name 参数传入要获取的实例的类名 首字母小写，这是默认的
     * @return
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
