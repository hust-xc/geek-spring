package org.geekbang.thinking.in.spring.conversion.bean.factory;

import org.geekbang.thinking.in.spring.conversion.ioc.overview.domain.User;

public interface UserFactory {

    default User cretaeUser() {
        return User.createUser();
    }
}
