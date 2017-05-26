package com.jakub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Jakub on 26.05.2017.
 */
@Controller
@Transactional
@EnableWebMvc
public class MainController {

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }
}
