package org.geekbang.thinking.in.spring.conversion.bean.definition;

import org.geekbang.thinking.in.spring.conversion.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.conversion.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        System.out.println("spring应用上下文已启动。。。");
        UserFactory user = applicationContext.getBean(UserFactory.class);
        System.out.println("spring应用上下文准备关闭。。。");
        applicationContext.close();
        System.out.println("spring应用上下文已关闭。。。");
    }

    @Bean(initMethod = "initUserFactory", destroyMethod = "destroyUserFactory")
    @Lazy(value = false)
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
