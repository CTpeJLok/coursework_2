package com.sport_objects.controllers;

import com.sport_objects.entities.Event;
import com.sport_objects.entities.EventDistribution;
import com.sport_objects.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/event-distribution")
public class EventDistributionController {

    @Autowired
    private EventDistributionService eventDistributionService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @RequestMapping("{event_id}")
    public String index(Model model, @PathVariable(name = "event_id") Long event_id) {
        model.addAttribute("title", "Результаты события " + event_id);
        model.addAttribute("event_id", event_id);
        model.addAttribute("eventDistributionList", eventDistributionService.findByEventId(event_id));

        return "event-distribution/index";
    }

    @RequestMapping("{event_id}/create")
    public ModelAndView create(@PathVariable(name = "event_id") Long event_id) {
        ModelAndView mav = new ModelAndView("event-distribution/create-edit");
        mav.getModelMap().addAttribute("title", "Добавить результат событию " + event_id);
        mav.getModelMap().addAttribute("event_id", event_id);
        mav.getModelMap().addAttribute("create", true);

        mav.addObject("eventDistribution", new EventDistribution(eventService.get(event_id)));
        mav.getModelMap().addAttribute("teamList", teamService.findAll(null));
        mav.getModelMap().addAttribute("userList", userService.findAll());
        mav.getModelMap().addAttribute("positionList", positionService.findAll());

        return mav;
    }

    @RequestMapping("{event_id}/edit/{event_distribution_id}")
    public ModelAndView edit(@PathVariable(name = "event_id") Long event_id,
                             @PathVariable(name = "event_distribution_id") Long event_distribution_id) {
        ModelAndView mav = null;

        if (eventDistributionService.isExist(event_distribution_id)) {
            mav = new ModelAndView("event-distribution/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование результата события " +
                    event_id);
            mav.getModelMap().addAttribute("event_id", event_id);
            mav.getModelMap().addAttribute("create", false);

            mav.addObject("eventDistribution", eventDistributionService.get(event_distribution_id));
            mav.getModelMap().addAttribute("teamList", teamService.findAll(null));
            mav.getModelMap().addAttribute("userList", userService.findAll());
            mav.getModelMap().addAttribute("positionList", positionService.findAll());

        } else
            mav = new ModelAndView("redirect:/place-sport-type/" + event_id);

        return mav;
    }

    @RequestMapping(value = "{event_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("eventDistribution") EventDistribution eventDistribution,
                       @PathVariable(name = "event_id") Long event_id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/event-distribution/" + event_id;

        try {
            eventDistributionService.save(eventDistribution);

            Date dateNow = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Event event = eventService.get(event_id);
            event.setUpdateDate(formatForDateNow.format(dateNow));

            try {
                eventService.save(event);
            } catch (Exception e) {

            }
        } catch (Exception e) {

        }

        return "redirect:/event-distribution/" + event_id;
    }

    @RequestMapping("{event_id}/delete/{event_distribution_id}")
    public String delete(@PathVariable(name = "event_id") Long event_id,
                         @PathVariable(name = "event_distribution_id") Long event_distribution_id) {
        eventDistributionService.del(event_distribution_id);

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Event event = eventService.get(event_id);
        event.setUpdateDate(formatForDateNow.format(dateNow));

        try {
            eventService.save(event);
        } catch (Exception e) {

        }

        return "redirect:/event-distribution/" + event_id;
    }

}
