package com.stone.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseController {

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String hello(HttpServletRequest request) {

        String hello = request.getParameter("hello");
        return hello;
    }
}
