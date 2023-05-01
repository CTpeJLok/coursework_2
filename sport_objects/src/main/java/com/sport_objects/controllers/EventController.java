package com.sport_objects.controllers;

import com.sport_objects.entities.Event;
import com.sport_objects.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService service;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        List<Event> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "События");

        return "event/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        Event event = new Event();

        model.addAttribute("obj", event);
        model.addAttribute("title", "Админ | Создание события");

        return "event/create";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("event/edit");
        Event event = service.get(id);
        mav.addObject("obj", event);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование события " + id);

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") Event event) {
        service.save(event);
        return "redirect:/event";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/event";
    }

}
