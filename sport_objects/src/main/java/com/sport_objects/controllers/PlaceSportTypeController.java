package com.sport_objects.controllers;

import com.sport_objects.entities.PlaceSportType;
import com.sport_objects.entities.SportType;
import com.sport_objects.services.PlaceService;
import com.sport_objects.services.PlaceSportTypeService;
import com.sport_objects.services.SportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/place-sport-type")
public class PlaceSportTypeController {

    @Autowired
    private PlaceSportTypeService placeSportTypeService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("{place_id}")
    public String index(Model model, @PathVariable(name = "place_id") Long place_id) {
        model.addAttribute("title", "Виды спорта объекта " + place_id);
        model.addAttribute("place_id", place_id);
        model.addAttribute("placeSportTypeList", placeSportTypeService.findByPlaceId(place_id));

        return "place-sport-type/index";
    }

    @RequestMapping("{place_id}/create")
    public ModelAndView create(@PathVariable(name = "place_id") Long place_id) {
        ModelAndView mav = new ModelAndView("place-sport-type/create-edit");
        mav.getModelMap().addAttribute("title", "Добавить вид спорта объекту " + place_id);
        mav.getModelMap().addAttribute("place_id", place_id);
        mav.getModelMap().addAttribute("create", true);
        mav.addObject("placeSportType", new PlaceSportType(placeService.get(place_id)));

        List<SportType> sportTypeList = sportTypeService.findAll(null);
        mav.getModelMap().addAttribute("sportTypeList", sportTypeList);

        return mav;
    }

    @RequestMapping("{place_id}/edit/{place_sport_type_id}")
    public ModelAndView edit(@PathVariable(name = "place_id") Long place_id,
                             @PathVariable(name = "place_sport_type_id") Long place_sport_type_id) {
        ModelAndView mav = null;

        if (placeSportTypeService.isExist(place_sport_type_id)) {
            mav = new ModelAndView("place-sport-type/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование вида спорта объекта " +
                    place_id);
            mav.getModelMap().addAttribute("place_id", place_id);
            mav.getModelMap().addAttribute("create", false);

            PlaceSportType placeSportType = placeSportTypeService.get(place_sport_type_id);
            mav.addObject("placeSportType", placeSportType);

        } else
            mav = new ModelAndView("redirect:/place-sport-type/" + place_id);

        return mav;
    }

    @RequestMapping(value = "{place_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("placeSportType") PlaceSportType placeSportType,
                       @PathVariable(name = "place_id") Long place_id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/place-sport-type/" + place_id;

        placeSportTypeService.save(placeSportType);

        return "redirect:/place-sport-type/" + place_id;
    }

    @RequestMapping("{place_id}/delete/{place_sport_type_id}")
    public String delete(@PathVariable(name = "place_id") Long place_id,
                         @PathVariable(name = "place_sport_type_id") Long place_sport_type_id) {
        placeSportTypeService.del(place_sport_type_id);

        return "redirect:/place-sport-type/" + place_id;
    }

}
