package com.sport_objects.controllers;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.entities.PlaceHelpfulType;
import com.sport_objects.services.HelpfulTypeService;
import com.sport_objects.services.PlaceHelpfulTypeService;
import com.sport_objects.services.PlaceService;
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
@RequestMapping("/place-helpful-type")
public class PlaceHelpfulTypeController {

    @Autowired
    private PlaceHelpfulTypeService placeHelpfulTypeService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private HelpfulTypeService helpfulTypeService;

    @RequestMapping("{place_id}")
    public String index(Model model, @PathVariable(name = "place_id") Long place_id) {
        model.addAttribute("title", "Полезные категории объекта " + place_id);
        model.addAttribute("place_id", place_id);
        model.addAttribute("placeHelpfulTypeList", placeHelpfulTypeService.findByPlaceId(place_id));

        return "place-helpful-type/index";
    }

    @RequestMapping("{place_id}/create")
    public ModelAndView create(@PathVariable(name = "place_id") Long place_id) {
        ModelAndView mav = new ModelAndView("place-helpful-type/create");
        mav.getModelMap().addAttribute("title", "Добавить полезную категорию объекту " +
                place_id);
        mav.getModelMap().addAttribute("place_id", place_id);
        mav.getModelMap().addAttribute("create", true);
        mav.addObject("placeHelpfulType", new PlaceHelpfulType(placeService.get(place_id)));

        List<HelpfulType> helpfulTypeList = helpfulTypeService.findAll(null);
        mav.getModelMap().addAttribute("helpfulTypeList", helpfulTypeList);

        return mav;
    }

    @RequestMapping(value = "{place_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("placeHelpfulType") PlaceHelpfulType placeHelpfulType,
                       @PathVariable(name = "place_id") Long place_id, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/place-helpful-type/" + place_id;

        try {
            placeHelpfulTypeService.save(placeHelpfulType);
        } catch (Exception e) {

        }

        return "redirect:/place-helpful-type/" + place_id;
    }

    @RequestMapping("{place_id}/delete/{place_helpful_type_id}")
    public String delete(@PathVariable(name = "place_id") Long place_id,
                         @PathVariable(name = "place_helpful_type_id") Long place_helpful_type_id) {
        placeHelpfulTypeService.del(place_helpful_type_id);
        return "redirect:/place-helpful-type/" + place_id;
    }

}
