package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

public interface UserFactory {

    default User cretaeUser() {
        return User.createUser();
    }
}
