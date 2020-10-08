package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        applicationContext.refresh();

        //测试BeanFactory的安全性
        displayBeanFactoryGetBean(applicationContext);
        //测试ObjectFactory的安全性
        displayObjectFactoryGetObject(applicationContext);
        //测试ObjectProvider的安全性
        displayObjectProviderGetObject(applicationContext);
        //测试ListableBeanFactory的安全性
        displayListableBeanFactoryGetBean(applicationContext);
        //测试ObjectProvider的stream的安全性
        displayObjectProviderStreamOps(applicationContext);


        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        System.err.println("displayObjectProviderStreamOps");
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderStreamOps", () -> beanProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBean(ListableBeanFactory beanFactory) {
        System.err.println("displayListableBeanFactoryGetBean");
        printBeanException("displayListableBeanFactoryGetBean", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetObject(AnnotationConfigApplicationContext applicationContext) {
        System.err.println("displayObjectProviderGetObject");
        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectProviderGetObject", () -> beanProvider.getIfAvailable());
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        System.err.println("displayObjectFactoryGetObject");
        ObjectFactory<User> beanProvider = applicationContext.getBeanProvider(User.class);
        printBeanException("displayObjectFactoryGetObject", () -> beanProvider.getObject());
    }

    private static void displayBeanFactoryGetBean(AnnotationConfigApplicationContext applicationContext) {
        System.err.println("displayBeanFactoryGetBean");
        printBeanException("displayBeanFactoryGetBean", () -> applicationContext.getBean(User.class));
    }

    public static void printBeanException(String source, Runnable runnable) {

        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
