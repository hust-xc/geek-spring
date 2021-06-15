package org.geekbang.thinking.in.spring.conversion.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class AutoWiringDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";

        reader.loadBeanDefinitions(location);

        UserHolder userHolder =(UserHolder) beanFactory.getBean("userHolder");

        System.out.println(userHolder);
    }
}
