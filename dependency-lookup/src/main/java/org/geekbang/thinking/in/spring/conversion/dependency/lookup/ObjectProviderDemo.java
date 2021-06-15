package org.geekbang.thinking.in.spring.conversion.dependency.lookup;

import org.geekbang.thinking.in.spring.conversion.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ObjectProviderDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ObjectProviderDemo.class);

        applicationContext.refresh();

        lookupObjectProvider(applicationContext);

        lookupIfAvaliable(applicationContext);

        lookupByStreamOps(applicationContext);

        applicationContext.close();

    }

    @Bean
    @Primary
    public String helloWorld() {
        return "helloWorld";
    }

    @Bean
    public String message() {
        return "Message";
    }

    public static void lookupIfAvaliable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        System.out.println(beanProvider.getIfAvailable(User::createUser));
    }

    public static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
//        for (String str : beanProvider) {
//            System.out.println(str);
//        }
        beanProvider.forEach(System.out::println);
    }

    public static void lookupObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
