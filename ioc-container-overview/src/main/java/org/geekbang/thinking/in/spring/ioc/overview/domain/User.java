package org.geekbang.thinking.in.spring.ioc.overview.domain;

import org.geekbang.thinking.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

public class User {

    private Long id;

    private String name;

    private City city;

    private Resource configLocation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("谢超");
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", configLocation=" + configLocation +
                '}';
    }
}
