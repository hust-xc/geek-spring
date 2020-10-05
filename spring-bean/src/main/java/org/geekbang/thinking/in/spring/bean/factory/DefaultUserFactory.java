package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 方式：DefaultUserFactory初始化中。。。");
    }

    public void initUserFactory() {
        System.out.println("自定义初始化方法：DefaultUserFactory初始化中。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("实现InitializingBean初始化方法：DefaultUserFactory初始化中。。。");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy 方式：DefaultUserFactory销毁中。。。");
    }

    public void destroyUserFactory() {
        System.out.println("自定义销毁方式：DefaultUserFactory销毁中。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("实现DisposableBean：DefaultUserFactory销毁中。。。");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("DefaultUserFactory已经被回收。。。");
    }
}
