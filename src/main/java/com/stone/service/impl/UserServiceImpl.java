package com.stone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.stone.dao.UserDao;
import com.stone.domain.User;
import com.stone.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findUserById(Long Id) {

        return userDao.findById(Id);
    }

    @Override
    public User findUserByName(String name) {

        return userDao.findUserByName(name);
    }

    @Override
    public List<User> findAllUser() {

        return userDao.findAll();
    }

}
