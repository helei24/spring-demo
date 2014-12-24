package com.stone.service;

import java.util.List;

import com.stone.domain.User;

public interface UserService {

    public User findUserById(Long id);

    public User findUserByName(String name);

    public List<User> findAllUser();
}
