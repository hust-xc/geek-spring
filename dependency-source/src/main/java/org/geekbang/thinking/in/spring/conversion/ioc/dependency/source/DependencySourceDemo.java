package org.geekbang.thinking.in.spring.conversion.ioc.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * 依赖来源示例
 * 这四个对象通过依赖查找找不到  但是通过依赖注入能注入进来
 * 所以这几个叫做非spring容器管理对象
 * 本质是因为依赖注入的来源比依赖查找的来源多了这一种
 *
 * 1.自定义注册bean -> 常规方式，如xml、api、注解产生的bean
 * 2.内建的bean -> 通过registerSingleton()
 * 3.内建的可注入依赖 -> 通过registerResolveDependency()
 */
public class DependencySourceDemo {

    // 注入在 postProcessProperties 方法执行，早于 setter注入，也早于 @PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initByInjection() {
        System.out.println("beanFactory == applicationContext:" + (beanFactory == applicationContext));
        System.out.println("beanFactory == applicationContext.getBeanFactory:" + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader == applicationContext:" + (resourceLoader == applicationContext));
        System.out.println("applicationEventPublisher == applicationContext:" + (applicationEventPublisher == applicationContext));

    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在 BeanFactory 中查找!");
        }
        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(DependencySourceDemo.class);

        applicationContext.refresh();

        applicationContext.close();
    }
}
