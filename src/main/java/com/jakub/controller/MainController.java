package com.jakub.controller;

import com.jakub.dao.UsersDAO;
import com.jakub.model.Users;
import com.jakub.validator.UsersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 26.05.2017.
 */
@Controller
@Transactional
@EnableWebMvc
public class MainController {

    @Autowired
    private UsersDAO usersDAO;
    @Autowired
    private UsersValidator usersValidator;

    @RequestMapping("/index")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    //metoda odpowiadająca za wyświetlanie widkou strony błędu 403
    @RequestMapping("/403")
    public String accesDenied() {
        return "/403";
    }

    //metoda odpowiadająca za wyświetlanie informacji o zalogowanym użytkowniku
    @RequestMapping(value = {"/accountInfo"}, method = RequestMethod.GET)
    public String accountInfo(Model model, Principal principal) {
        String username = principal.getName();
        System.out.println("Username::: " + username);
        return "accountInfo";
    }

    //metoda odpowiadająca za wylogowywanie
    @RequestMapping("/logout")
    public String logout() {
        return "login?logout";
    }


    //metoda odpowiadająca za wyświetlanie strony logowania
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public Model login(Model model, @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            model.addAttribute("error", "Niepoprawna nazwa użytkownika lub hasło");
        }

        if (logout != null) {
            model.addAttribute("msg", "Wylogowano pomyslnie");
        }

        model.addAttribute("login");
        return model;
    }

    //metoda odpowiadająca za wyświetlanie strony rejestracji
    @RequestMapping(value = {"/registration"}, method = RequestMethod.POST)
    public ModelAndView registration(ModelMap registration) {
        ModelAndView model = new ModelAndView("registration");
        populateDefaultModel(model);
        registration.addAttribute("users", new Users());

        return model;
    }

    private void populateDefaultModel(ModelAndView model) {
        List<String> rola = new ArrayList<String>();
        rola.add("student");
        rola.add("promotor");

        model.addObject("rola", rola);
    }

    //metoda sprawdzająca poprawność danych wejściowych przy dodawaniu nowego produktu
    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("target = " + target);
        if (target.getClass() == Users.class) {
            dataBinder.setValidator(usersValidator);
        }
    }

    //metoda zapisująca nowy produkt do bazy danych
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, ModelAndView m, @Validated Users users, BindingResult bindingResult) throws IOException {
        ModelAndView model = new ModelAndView("login");

        if (bindingResult.hasErrors()) {
            populateDefaultModel(model);
            model.addObject("css", "error");
            model.addObject("msg", "Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie!");
            model.setViewName("registration");
            return model;
        }

        usersDAO.add(users.getLogin(), users.getHaslo(), users.getImie(), users.getNazwisko(), users.getRola());
        model.addObject("css", "msgSuccess");
        model.addObject("msg", "Zarejestrowano poprawnie!");
        populateDefaultModel(model);

        users.setLogin("");
        users.setHaslo("");
        users.setImie("");
        users.setNazwisko("");

        return model;
    }

}
