package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyLookupDemo {

    public static void main(String[] args) {
        //配置xml文件
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        //按类型查找
//        lookupByType(beanFactory);
//
//        lookupInRealTime(beanFactory);

//        lookupInLazy(beanFactory);

//        lookupCollectionByType(beanFactory);

        //按注解查找
        lookupByAnnotationType(beanFactory);


    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找:" + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找:" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查到所有user的集合对象:" + userMap);
        }
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("按照注解查找:" + userMap);
        }
    }
}
