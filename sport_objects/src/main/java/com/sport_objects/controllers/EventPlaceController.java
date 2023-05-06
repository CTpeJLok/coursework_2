package com.sport_objects.controllers;

import com.sport_objects.entities.EventPlace;
import com.sport_objects.services.EventPlaceService;
import com.sport_objects.services.EventService;
import com.sport_objects.services.PlaceSportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/event-place")
public class EventPlaceController {

    @Autowired
    private EventPlaceService eventPlaceService;

    @Autowired
    private PlaceSportTypeService placeSportTypeService;

    @Autowired
    private EventService eventService;

    @RequestMapping("{event_id}")
    public String index(Model model, @PathVariable(name = "event_id") Long event_id) {
        model.addAttribute("title", "Места события " + event_id);
        model.addAttribute("event_id", event_id);
        model.addAttribute("eventPlaceList", eventPlaceService.findByEventId(event_id));

        return "event-place/index";
    }

    @RequestMapping("{event_id}/create")
    public ModelAndView create(@PathVariable(name = "event_id") Long event_id) {
        ModelAndView mav = new ModelAndView("event-place/create-edit");
        mav.getModelMap().addAttribute("title", "Добавить место событию " + event_id);
        mav.getModelMap().addAttribute("event_id", event_id);
        mav.addObject("eventPlace", new EventPlace(eventService.get(event_id)));
        mav.getModelMap().addAttribute("placeSportTypes", placeSportTypeService.findAll());

        return mav;
    }

    @RequestMapping("{event_id}/edit/{event_place_id}")
    public ModelAndView edit(@PathVariable(name = "event_id") Long event_id,
                             @PathVariable(name = "event_place_id") Long event_place_id) {
        ModelAndView mav = null;

        if (eventPlaceService.isExist(event_place_id)) {
            mav = new ModelAndView("event-place/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование места события " + event_id);
            mav.getModelMap().addAttribute("event_id", event_id);
            mav.addObject("eventPlace", eventPlaceService.get(event_place_id));
            mav.getModelMap().addAttribute("placeSportTypes", placeSportTypeService.findAll());
        } else
            mav = new ModelAndView("redirect:/event-place/" + event_id);

        return mav;
    }

    @RequestMapping(value = "{event_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("eventPlace") EventPlace eventPlace,
                       @PathVariable(name = "event_id") Long event_id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/event-place/" + event_id;

        eventPlaceService.save(eventPlace);
        return "redirect:/event-place/" + event_id;
    }

    @RequestMapping("{event_id}/delete/{event_place_id}")
    public String delete(@PathVariable(name = "event_id") Long event_id,
                         @PathVariable(name = "event_place_id") Long event_place_id) {
        eventPlaceService.del(event_place_id);
        return "redirect:/event-place/" + event_id;
    }

}
