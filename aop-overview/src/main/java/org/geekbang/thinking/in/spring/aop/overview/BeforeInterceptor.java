package org.geekbang.thinking.in.spring.aop.overview;

import java.lang.reflect.Method;

public interface BeforeInterceptor {

    /**
     * 前置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    Object before(Object proxy, Method method, Object[] args);
}
