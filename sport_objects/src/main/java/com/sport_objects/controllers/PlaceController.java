package com.sport_objects.controllers;

import com.sport_objects.entities.Place;
import com.sport_objects.services.PlaceService;
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
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Объекты");
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("placeList", placeService.findAll(searchKeyword));

        return "place/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание объекта");
        model.addAttribute("place", new Place());

        return "place/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (placeService.isExist(id)) {
            mav = new ModelAndView("place/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование объекта " + id);
            mav.addObject("place", placeService.get(id));

        } else
            mav = new ModelAndView("redirect:/place");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") Place place, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/place";

        placeService.save(place);
        return "redirect:/place";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        placeService.del(id);
        return "redirect:/place";
    }

}
