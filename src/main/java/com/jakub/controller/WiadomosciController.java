package com.jakub.controller;

import com.jakub.dao.UsersDAO;
import com.jakub.dao.WiadomosciDAO;
import com.jakub.model.Users;
import com.jakub.model.Wiadomosci;
import com.jakub.validator.UsersValidator;
import com.jakub.validator.WiadomosciValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 29.05.2017.
 */
@Controller
@Transactional
@EnableWebMvc
@RequestMapping("/wiadomosci")
public class WiadomosciController {

    @Autowired
    UsersDAO usersDAO;

    @Autowired
    WiadomosciDAO wiadomosciDAO;

    @Autowired
    WiadomosciValidator wiadomosciValidator;

    @Autowired
    UsersValidator usersValidator;

    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("target = " + target);
        if (target.getClass() == Wiadomosci.class) {
            dataBinder.setValidator(wiadomosciValidator);
        }
    }

    @RequestMapping("/list")
    public ModelAndView list(Principal principal) {
        ModelAndView model = new ModelAndView("MessageList");
        Users user = usersDAO.findUser(principal.getName());
        List<Wiadomosci> messages = wiadomosciDAO.showall(user.getIduser());
        model.addObject("messages", messages);
        List<Users> users = new ArrayList<Users>();

        for (Wiadomosci w : messages) {
            users.add(usersDAO.findUserByID(w.getIdnadawcy()));
        }
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/delete/{idwiadomosci}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("idwiadomosci") int idwiadomosci) {
        ModelAndView model = new ModelAndView("redirect:/wiadomosci/list");
        wiadomosciDAO.deleteMessage(idwiadomosci);
        return model;
    }

    @RequestMapping("/listsent")
    public ModelAndView listsent(Principal principal) {
        ModelAndView model = new ModelAndView("sentMessages");
        Users user = usersDAO.findUser(principal.getName());
        List<Wiadomosci> messages = wiadomosciDAO.showallsent(user.getIduser());
        model.addObject("messages", messages);
        List<Users> users = new ArrayList<Users>();

        for (Wiadomosci w : messages) {
            users.add(usersDAO.findUserByID(w.getIdadresata()));
        }
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/deletesent/{idwiadomosci}", method = RequestMethod.GET)
    public ModelAndView deletesent(@PathVariable("idwiadomosci") int idwiadomosci) {
        ModelAndView model = new ModelAndView("redirect:/wiadomosci/listsent");
        wiadomosciDAO.deleteMessage(idwiadomosci);
        return model;
    }

    //metoda odpowiadająca za wyświetlanie strony rejestracji
    @RequestMapping(value = {"/messages"}, method = RequestMethod.GET)
    public ModelAndView messages(ModelMap messages) {
        ModelAndView model = new ModelAndView("newMessage");
        messages.addAttribute("users", new Users());
        messages.addAttribute("messages", new Wiadomosci());
        return model;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("users") Users users, Principal principal, HttpServletRequest request, ModelAndView m, @ModelAttribute("messages") @Validated Wiadomosci wiadomosci, BindingResult bindingResult) throws IOException {
        ModelAndView model = new ModelAndView("newMessage");

        if (bindingResult.hasErrors()) {
            model.addObject("css", "error");
            model.addObject("msg", "Nie wprowadzono wszystkich danych lub wprowadzono je niepoprawnie!");
            return model;
        }

        Users nadawca = usersDAO.findUser(principal.getName());

        if (users.getLogin().equals("")) {
            model.addObject("css", "error");
            model.addObject("msg", "Nie podano adresata!");
            return model;
        }

        if (usersDAO.findUser(users.getLogin())==null) {
            model.addObject("css", "error");
            model.addObject("msg", "Podany adresat nie istnieje!");
            return model;
        }
        Users adresat = usersDAO.findUser(users.getLogin());


        wiadomosciDAO.add(nadawca.getIduser(), adresat.getIduser(), wiadomosci.getTemat(), wiadomosci.getTresc());
        model.addObject("css", "msgSuccess");
        model.addObject("msg", "Wiadomość została wysłana!");

        wiadomosci.setTemat("");
        wiadomosci.setTresc("");

        return model;
    }

}