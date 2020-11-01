package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * bean 元信息配置示例
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "GBK");
        int beanDefinitions = reader.loadBeanDefinitions(encodedResource);
        System.out.println("读取的beanDefinition个数" + beanDefinitions);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }
}
