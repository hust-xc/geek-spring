package org.geekbang.thinking.in.spring.conversion.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class UsersNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 将 "user" 元素注册对应的 BeanDefinitionParser 实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
