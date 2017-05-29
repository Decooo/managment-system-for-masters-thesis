package com.jakub.controller;

import com.jakub.dao.UsersDAO;
import com.jakub.dao.WiadomosciDAO;
import com.jakub.model.Users;
import com.jakub.model.Wiadomosci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
        List<Wiadomosci> messages = wiadomosciDAO.showall(user.getIduser());
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

}
