package org.geekbang.thinking.in.spring.conversion.ioc.bean.scope.web;

import org.geekbang.thinking.in.spring.conversion.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * webmvc配置类
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
        return user;
    }
}
