package com.stone.service.impl;

import com.stone.dao.UserDao;
import com.stone.domain.User;
import com.stone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
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

  @Override
  public boolean validateUser(User user) {

    List<User> users = userDao.findByExample(user);
    return !users.isEmpty();
  }
}
