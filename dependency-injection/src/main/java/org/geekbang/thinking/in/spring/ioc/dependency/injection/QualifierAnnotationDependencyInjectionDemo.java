package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

/**
 * 通过@Qualifier注解进行限定注入
 */
@Configuration
public class QualifierAnnotationDependencyInjectionDemo {


    @Autowired
    private User superUser;

    @Autowired
    @Qualifier("user")
    private User user;

    @Autowired
    private Collection<User> allUsers;  // superUser + user

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; //user7 + user8 + user9 + user10

    @Autowired
    @UserGroup
    private Collection<User> groupUsers; // user9 + user10

    @Bean
    @Qualifier
    public static User user1() {
        return createUser(7L);
    }
    @Bean
    @Qualifier
    public static User user2() {
        return createUser(8L);
    }
    @Bean
    @UserGroup
    public static User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public static User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(applicationContext);
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        reader.loadBeanDefinitions(location);

        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

//        System.out.println(demo.superUser);
//        System.out.println(demo.user);

        System.out.println(demo.qualifierUsers);
        System.out.println(demo.allUsers);
        System.out.println(demo.groupUsers);

        applicationContext.close();
    }
}
