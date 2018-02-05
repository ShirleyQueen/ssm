package com.xpfirst.hdrRouter.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C) 北京学信科技有限公司
 *
 * @Des:
 * @Author: Gaojindan
 * @Create: 2018/1/24 上午10:56
 **/
@Controller
@RequestMapping(value = "pf")
public class PFController {
    private static final Logger log = LoggerFactory.getLogger(NunjucksController.class);

    @RequestMapping(value = "vote")
    public ModelAndView vote(ModelMap modelMap, HttpServletRequest request){

        log.info("=============>投票开始了");
        return new ModelAndView("pf/vote");
    }
}
