package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //配置xml文件
        //启动spring应用上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);

        System.out.println(userRepository.getUsers());

        System.out.println(userRepository.getBeanFactory() == applicationContext);

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject() == applicationContext);


    }


}
