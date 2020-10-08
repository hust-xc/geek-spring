package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 层次性的bean查找
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        // 1. 获取 HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();

        System.out.println("当前bean的parentBeanFactory：" + beanFactory.getParentBeanFactory());

        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);

        System.out.println("当前bean的parentBeanFactory：" + beanFactory.getParentBeanFactory());

        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");

        displayContainsBean(beanFactory, "user");


        applicationContext.refresh();

        applicationContext.close();
    }



    public static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("此beanFactory[%s] 是否包含 beanName[%s]: %s \n", beanFactory, beanName, beanFactory.containsLocalBean(beanName));
    }

    public static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("此beanFactory[%s] 是否包含 beanName[%s]: %s \n", beanFactory, beanName, containsBean(beanFactory, beanName));
    }

    public static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory hierarchicalBeanFactory = (HierarchicalBeanFactory)parentBeanFactory;
            if (containsBean(hierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    public static ConfigurableListableBeanFactory createParentBeanFactory() {

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        String location= "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);
        return beanFactory;
    }
}
