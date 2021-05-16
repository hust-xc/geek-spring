package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * PropertiesBeanDefinitionReader示例
 */
public class PropertiesBeanDefinitionReaderDemo {

    public static void main(String[] args) {
        //初始话ioc容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建面向properties资源的BeanDefinitionReader
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        //Properties加载默认通过iso-8859-1读取  实际存储是utf-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        //通过指定的classpath获取resource
        Resource resource = resourceLoader.getResource("classpath:/META-INF/user-bean-definitions.properties");
        //转换成带有字符编码的resource
        EncodedResource encodedResource = new EncodedResource(resource, "utf-8");
        int beanDefinitions = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(String.format("已加载 %d 个bean", beanDefinitions));

        User user = beanFactory.getBean(User.class);
        System.out.println(user);


    }
}
