package com.stone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.stone.domain.User;
import com.stone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

  @Autowired
//  @Qualifier("userServiceImpl")
  UserService userService;

  @RequestMapping(value = {"", "/", "/login"}, method = GET)
  public String toLogin() {

    return "login";
  }

  @RequestMapping(value = "/login", method = POST)
  public String toIndex(HttpServletRequest request, @ModelAttribute User user) {


    if(userService.validateUser(user)){
      return "index";
    }
    return "login";
  }

}
