package com.jakub.controller;

import com.jakub.dao.PraceDyplomoweDAO;
import com.jakub.dao.TematyDAO;
import com.jakub.dao.UsersDAO;
import com.jakub.model.Tematy;
import com.jakub.model.Users;
import com.jakub.model.Wiadomosci;
import com.jakub.validator.TematyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jakub on 01.06.2017.
 */
@Transactional
@Controller
@RequestMapping("/tematy")
public class TematyController {

    @Autowired
    UsersDAO usersDAO;
    @Autowired
    TematyDAO tematyDAO;

    @Autowired
    PraceDyplomoweDAO praceDyplomoweDAO;

    @Autowired
    TematyValidator tematyValidator;

    @InitBinder
    public void myInitBuilder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("target = " + target);
        if (target.getClass() == Tematy.class) {
            dataBinder.setValidator(tematyValidator);
        }
    }

    @RequestMapping("/lista")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("topicList");
        List<Tematy> topics = tematyDAO.showall();
        model.addObject("topics", topics);
        List<Users> users = new ArrayList<Users>();

        for (Tematy t : topics) {
            users.add(usersDAO.findUserByID(t.getIdpromotora()));
        }
        model.addObject("users", users);
        return model;
    }

    @RequestMapping("/listawolnych")
    public ModelAndView freelist() {
        ModelAndView model = new ModelAndView("listFreeThemes");
        List<Tematy> topics = tematyDAO.showFreeTopics();
        model.addObject("topics", topics);
        List<Users> users = new ArrayList<Users>();

        for (Tematy t : topics) {
            users.add(usersDAO.findUserByID(t.getIdpromotora()));
        }
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/nowy", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView model = new ModelAndView("newTopic");
        model.addObject("topic", new Tematy());
        return model;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(Principal principal, HttpServletRequest request, ModelAndView m, @ModelAttribute("topic") @Validated Tematy tematy, BindingResult bindingResult) throws IOException {
        ModelAndView model = new ModelAndView("newTopic");

        if (bindingResult.hasErrors()) {
            model.addObject("css", "error");
            model.addObject("msg", "Nie wprowadzono poprawnie nazwy tematu");
            return model;
        }

        Users promotor = usersDAO.findUser(principal.getName());

        tematyDAO.add(promotor.getIduser(), tematy.getTemat());
        model.addObject("css", "msgSuccess");
        model.addObject("msg", "Dodoano nowy temat!");
        tematy.setTemat("");
        return model;
    }

    @RequestMapping(value = "/reserve/{idtematy}", method = RequestMethod.GET)
    public ModelAndView reserve(HttpServletRequest request, @PathVariable("idtematy") int idTematy, Principal principal, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/tematy/listawolnych");
        Users user = usersDAO.findUser(principal.getName());
        if (tematyDAO.itIsAlreadyBooked(user.getIduser())) {
            attributes.addFlashAttribute("css", "error");
            attributes.addFlashAttribute("msg", "Nie mozesz zarezerwowac więcej tematow");
            return model;
        }
        if (praceDyplomoweDAO.isAlreadyAssigned(user.getIduser())) {
            attributes.addFlashAttribute("css", "error");
            attributes.addFlashAttribute("msg", "Temat pracy dyplomowej został już wybrany. Nie mozesz zarezerwowac nastepnego tematu.");
            return model;
        }

        attributes.addFlashAttribute("css", "msgSuccess");
        attributes.addFlashAttribute("msg", "Poprawnie zarezerwowano temat pracy dyplomowej");
        tematyDAO.reserveTopic(idTematy, user.getIduser());
        return model;
    }

    @RequestMapping("/rezerwacje")
    public ModelAndView reservation() {
        ModelAndView model = new ModelAndView("reservation");
        List<Tematy> topics = tematyDAO.showallReservation();
        model.addObject("topics", topics);
        List<Users> users = new ArrayList<Users>();

        for (Tematy t : topics) {
            users.add(usersDAO.findUserByID(t.getIdUser()));
        }
        model.addObject("users", users);
        return model;
    }

    @RequestMapping(value = "/accept/{idtematy}", method = RequestMethod.GET)
    public ModelAndView accept(Principal principal, @PathVariable("idtematy") int idtematy, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/tematy/rezerwacje");
        Users user = usersDAO.findUser(principal.getName());
        tematyDAO.acceptReservation(idtematy, user.getIduser());
        attributes.addFlashAttribute("css", "msgSuccess");
        attributes.addFlashAttribute("msg", "Zaakceptowano rezerwacje tematu");
        return model;
    }

    @RequestMapping(value = "/reject/{idtematy}", method = RequestMethod.GET)
    public ModelAndView reject(Principal principal, @PathVariable("idtematy") int idtematy, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("redirect:/tematy/rezerwacje");
        tematyDAO.rejectReservation(idtematy);
        attributes.addFlashAttribute("css", "msgSuccess");
        attributes.addFlashAttribute("msg", "Odrzucono rezerwacje tematu");
        return model;
    }

}
