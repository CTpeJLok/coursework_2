package com.sport_objects.controllers;

import com.sport_objects.entities.EventPlace;
import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.services.EventPlaceService;
import com.sport_objects.services.EventService;
import com.sport_objects.services.PlaceSportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/event-place")
public class EventPlaceController {

    @Autowired
    private EventPlaceService service;

    @Autowired
    private PlaceSportTypeService placeSportTypeService;

    @Autowired
    private EventService eventService;

    @RequestMapping("{id}")
    public String index(Model model, @PathVariable(name = "id") Long id) {
        List<EventPlace> eventPlaces = service.findEventPlacesByEventId(id);

        model.addAttribute("eventId", id);
        model.addAttribute("List", eventPlaces);
        model.addAttribute("title", "Админ | места события " + id);

        return "event-place/index";
    }

    @RequestMapping("{id}/add")
    public ModelAndView add(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("event-place/add");
        EventPlace eventPlace = new EventPlace(eventService.get(id));
        mav.addObject("obj", eventPlace);

        List<PlaceSportType> placeSportTypes = placeSportTypeService.findAll();

        mav.getModelMap().addAttribute("eventId", id);
        mav.getModelMap().addAttribute("title", "Админ | Добавить место для события");
        mav.getModelMap().addAttribute("placeSportTypes", placeSportTypes);

        return mav;
    }

    @RequestMapping("{id}/edit/{place_sport_type_id}")
    public ModelAndView addUser(@PathVariable(name = "id") Long id, @PathVariable(name = "place_sport_type_id") Long place_sport_type_id) {
        ModelAndView mav = new ModelAndView("event-place/edit");
        List<EventPlace> eventPlaces = service.findEventPlacesByEventId(id);
        for (EventPlace ep : eventPlaces) {
            if (ep.getPlaceSportType().getPlace().getId() == place_sport_type_id) {
                mav.addObject("obj", ep);
                break;
            }
        }

        List<PlaceSportType> placeSportTypes = placeSportTypeService.findAll();

        mav.getModelMap().addAttribute("eventId", id);
        mav.getModelMap().addAttribute("title", "Админ | Редактирование места для события");
        mav.getModelMap().addAttribute("placeSportTypes", placeSportTypes);

        return mav;
    }

    @RequestMapping(value = "{id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") EventPlace eventPlace, @PathVariable(name = "id") Long id) {
        service.save(eventPlace);

        return "redirect:/event-place/" + id;
    }

    @RequestMapping("{id}/delete/{place_sport_type_id}")
    public String delete(@PathVariable(name = "id") Long id, @PathVariable(name = "place_sport_type_id") Long place_sport_type_id) {
        List<EventPlace> eventPlaces = service.findEventPlacesByEventId(id);
        for (EventPlace ep : eventPlaces) {
            if (ep.getPlaceSportType().getPlace().getId() == place_sport_type_id) {
                service.del(ep.getId());
                break;
            }
        }

        return "redirect:/event-place/" + id;
    }

}
