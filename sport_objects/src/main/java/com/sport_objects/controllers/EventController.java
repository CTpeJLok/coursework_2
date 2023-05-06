package com.sport_objects.controllers;

import com.sport_objects.entities.Event;
import com.sport_objects.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "События");
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("eventList", eventService.findAll(searchKeyword));

        return "event/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание события");
        model.addAttribute("event", new Event());

        return "event/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (eventService.isExist(id)) {
            mav = new ModelAndView("event/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование события " + id);
            mav.addObject("event", eventService.get(id));
        } else
            mav = new ModelAndView("redirect:/event");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("event") Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/event";

        eventService.save(event);
        return "redirect:/event";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        eventService.del(id);
        return "redirect:/event";
    }

}
