package com.stone.authentication.service;

import com.stone.domain.User;

public interface AuthenticationService {

  boolean validateUser(User user);
}
