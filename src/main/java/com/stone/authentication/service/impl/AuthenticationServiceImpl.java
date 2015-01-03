package com.stone.authentication.service.impl;

import static com.google.common.collect.Lists.newArrayList;

import com.stone.dao.UserDao;
import com.stone.authentication.Role;
import com.stone.domain.User;
import com.stone.authentication.UserLoginDetails;
import com.stone.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService,UserDetailsService {

  @Autowired
  UserDao userDao;

  @Override
  public boolean validateUser(User user) {
    return false;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    UserDetails userDetails = null;
    User user = userDao.findUserByName(username.toLowerCase());

    final List<GrantedAuthority> authorities = getAuthoritiesForUser(user);

    return  new UserLoginDetails(user.getName(),user.getPassword(),true,authorities);
  }

  private List<GrantedAuthority> getAuthoritiesForUser(User user) {

    List<GrantedAuthority> grantedAuthorities = newArrayList();
    final Set<Role> userRoles = user.getRoles();

    addRoles(userRoles, grantedAuthorities);

    return null;
  }

  private void addRoles(final Set<Role> userRoles, List<GrantedAuthority> authorities) {

    if (authorities == null) {
      authorities = new ArrayList<GrantedAuthority>();
    }
    for (final Role role : userRoles) {
      final String roleName = role.getName();
      final GrantedAuthority newAuth = new SimpleGrantedAuthority(roleName);
      authorities.add(newAuth);
    }
  }
}
