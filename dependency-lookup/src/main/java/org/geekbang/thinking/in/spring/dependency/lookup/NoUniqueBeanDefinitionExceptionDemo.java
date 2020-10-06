package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        applicationContext.refresh();
        try {
            applicationContext.getBean(String.class);
        } catch (NoSuchBeanDefinitionException e) {
            e.printStackTrace();
        }
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }
}
