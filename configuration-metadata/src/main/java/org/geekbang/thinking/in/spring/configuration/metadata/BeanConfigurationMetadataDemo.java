package org.geekbang.thinking.in.spring.configuration.metadata;


import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * bean 配置元信息示例
 */
public class BeanConfigurationMetadataDemo {

    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("name", "joshua");
        //获取abstractBeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //附加属性（不影响bean的 populate、intialize）
        beanDefinition.setAttribute("name", "xiechao");
        //当前bean来自何方
        beanDefinition.setSource(BeanConfigurationMetadataDemo.class);

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if ("user".equals(beanName) && User.class.equals(bean.getClass())) {

                    if (beanDefinition.getSource().equals(BeanConfigurationMetadataDemo.class)) {
                        String name = (String) beanDefinition.getAttribute("name");
                        User user = (User) bean;
                        user.setName(name);
                        return user;
                    }
                }
                return bean;
            }
        });

        //注册user的beanDefinition
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", User.class);

        System.out.println(user);
    }
}
