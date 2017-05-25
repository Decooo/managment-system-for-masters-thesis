package com.jakub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.Principal;

/**
 * Created by Jakub on 23.03.2017.
 */

//klasa obsługująca główny kontroler aplikacji, który odpowiada za wyświetlanie strony głównej, logowania i rejestracji nowego użytkownika

@Controller
@Transactional
@EnableWebMvc
public class MainController {


//metoda odpowiadająca za wyświetlanie widkou strony głównej
    @RequestMapping("/index")
    public ModelAndView index() {

        return new ModelAndView("index");
    }

//metoda odpowiadająca za wyświetlanie widkou strony głównej
    @RequestMapping("/")
    public ModelAndView home() {

        return new ModelAndView("index");
    }

//metoda odpowiadająca za wyświetlanie widkou strony błędu 403
    @RequestMapping("/403")
    public String accesDenied() {
        return "/403";
    }

}

