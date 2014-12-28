package com.stone.service;

import com.stone.domain.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long id);

    public User findUserByName(String name);

    public List<User> findAllUser();

  boolean validateUser(User user);
}
