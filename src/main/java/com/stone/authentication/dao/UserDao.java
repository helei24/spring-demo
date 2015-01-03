package com.stone.authentication.dao;

import com.stone.common.hibernate.GenericDao;

public interface UserDao extends GenericDao<User, Long> {

    public User findUserByName(String name);
}
