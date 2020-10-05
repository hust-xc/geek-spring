package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建外部的userFactory对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        //注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);

        applicationContext.refresh();

        UserFactory userFactoryByLookUp = applicationContext.getBean(UserFactory.class);

        System.out.println("userFactory == userFactoryByLookUp:" + (userFactory == userFactoryByLookUp));

        applicationContext.close();
    }
}
