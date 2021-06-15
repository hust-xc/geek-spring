package org.geekbang.thinking.in.spring.conversion.bean.definition;

import org.geekbang.thinking.in.spring.conversion.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

//通过import方式导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建beanFactory容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        //注册配置类
        annotationConfigApplicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 通过 BeanDefinition 注册 API 实现
        // 1.命名 Bean 的注册方式
        registerUserBeanDefinition(annotationConfigApplicationContext, "xc-user");
        // 2. 非命名 Bean 的注册方法
        registerUserBeanDefinition(annotationConfigApplicationContext);

        //启动应用上下文
        annotationConfigApplicationContext.refresh();

        System.out.println("Config类型的所有bean：" + annotationConfigApplicationContext.getBeansOfType(Config.class));
        System.out.println("User类型的所有bean：" + annotationConfigApplicationContext.getBeansOfType(User.class));


        //关闭应用上下文
        annotationConfigApplicationContext.close();
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1L)
                .addPropertyValue("name", "谢超");
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    private static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }

    //通过@Component方式定义
    @Component
    public static class Config {

        //1.通过@Bean方式定义
        @Bean(name = {"user", "xiechao-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("谢超");
            return user;
        }
    }

}
