package org.geekbang.thinking.in.spring.conversion.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.conversion.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //配置xml文件
        //启动spring应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

        //自定义bean
        UserRepository userRepository = beanFactory.getBean(UserRepository.class);

        System.out.println(userRepository.getUsers());

        //依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory() == beanFactory);

        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();

        System.out.println(objectFactory.getObject() == beanFactory);

        Environment environment = beanFactory.getBean(Environment.class);

        System.out.println("获取 Environment 类型的bean：" + environment);



    }


}
