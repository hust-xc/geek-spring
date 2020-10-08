package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        applicationContext.refresh();
        applicationContext.close();
        System.out.println("spring应用上下文已关闭。。。");
        Thread.sleep(5000);
        System.gc();
        Thread.sleep(5000);
    }
}
