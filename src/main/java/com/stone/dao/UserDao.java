package com.stone.dao;

import com.stone.common.hibernate.GenericDao;
import com.stone.domain.User;

public interface UserDao extends GenericDao<User, Long> {

    public User findUserByName(String name);
}
