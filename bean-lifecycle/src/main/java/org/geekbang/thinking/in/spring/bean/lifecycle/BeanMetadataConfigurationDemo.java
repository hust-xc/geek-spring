package org.geekbang.thinking.in.spring.bean.lifecycle;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/**
 * bean 元信息配置示例
 */
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        String location = "META-INF/user.properties";
        int beanDefinitions = reader.loadBeanDefinitions(location);
        System.out.println("读取的beanDefinition个数" + beanDefinitions);
    }
}
