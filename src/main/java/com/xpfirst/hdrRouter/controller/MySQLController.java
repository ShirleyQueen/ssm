package com.xpfirst.hdrRouter.controller;

import com.xpfirst.hdrRouter.entity.User;
import com.xpfirst.hdrRouter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2018/1/29 下午1:10
 **/

@Controller
@RequestMapping(value = "mysql")
public class MySQLController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value="/")
    public ModelAndView login(ModelMap modelMap){
        return new ModelAndView("mysql/index");
    }
    @RequestMapping(value="/rollback")
    public ModelAndView login(@RequestParam(value = "username", required = true) String username,
                              ModelMap modelMap){
        User user = userService.findUserByUsername(username);
        modelMap.put("user", user);
        return new ModelAndView("mysql/index");
    }
}