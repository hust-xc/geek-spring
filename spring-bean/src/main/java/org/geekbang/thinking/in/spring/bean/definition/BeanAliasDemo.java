package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        User xiechaoUser = beanFactory.getBean("xiechao-user", User.class);

        User user = beanFactory.getBean("user", User.class);

        System.out.println("xiechao-user 和 user bean是否相等：" + (xiechaoUser == user));
    }
}
