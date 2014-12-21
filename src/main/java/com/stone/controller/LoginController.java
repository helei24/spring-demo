package com.stone.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = GET)
    public String toLogin() {

        return "login";
    }

    @RequestMapping(value = { "", "/", "/index" }, method = GET)
    public String toIndex(HttpServletRequest request) {

        System.out.println("s");
        return "index";
    }
}
