package org.geekbang.thinking.in.spring.configuration.metadata;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * 基于java注解  spring ioc 容器配置元信息示例
 */
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/user-bean-definitions.properties") // Java 8+ @Repeatable 支持
@PropertySource("classpath:/META-INF/user-bean-definitions.properties")
// @PropertySources(@PropertySource(...))
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {


    /**
     * user.name 是 Java Properties 默认存在，当前用户：mercyblitz，而非配置文件中定义"小马哥"
     * @param id
     * @param name
     * @return
     */
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }

    public static void main(String[] args) {



        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类为config class
        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        //启动应用上下文
        context.refresh();

        Map<String, User> beansOfType = context.getBeansOfType(User.class);

        for (Map.Entry<String, User> userEntry : beansOfType.entrySet()) {
            System.out.println(String.format("beanName: %s, bean: %s", userEntry.getKey(), userEntry.getValue()));
        }

        //关闭应用上下问
        context.close();

    }
}
