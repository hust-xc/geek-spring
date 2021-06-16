package org.geekbang.thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring 事件
 */
public class MySpringEvent extends ApplicationEvent {
    public MySpringEvent(Object source) {
        super(source);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }

}
