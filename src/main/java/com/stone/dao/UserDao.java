package com.stone.dao;

import java.util.List;

import com.stone.domain.User;

public interface UserDao {

    public User findUserById(int id);

    public User findUserByName(String name);

    public List<User> findAllUser();
}
