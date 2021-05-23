package org.geekbang.thinking.in.spring.resource;

import org.geekbang.thinking.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * 注入 {@link Resource} 对象示例
 */
public class InjectingResourceDemo {


    @Value("classpath:/META-INF/default.properties")
    private Resource defaultResource;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] resources;

    @Value("${user.dir}")
    private String userDir;

    @PostConstruct
    private void init() {
        System.out.println(ResourceUtils.getContent(defaultResource));
        System.out.println("==================================");
        Stream.of(resources).map(ResourceUtils::getContent).forEach(System.out::println);
        System.out.println("==================================");
        System.out.println(userDir);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为 Configuration Class
        context.register(InjectingResourceDemo.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // 关闭 Spring 应用上下文
        context.close();
    }
}
