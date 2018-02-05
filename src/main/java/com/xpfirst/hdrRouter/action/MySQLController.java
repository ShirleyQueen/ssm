package com.xpfirst.hdrRouter.action;

import com.xpfirst.hdrRouter.model.User;
import com.xpfirst.hdrRouter.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ModelAndView rollback(){

        User user = new User();
        user.setUsername("100009");
        userService.insetUsername(user);
        return new ModelAndView("mysql/index");
    }
}