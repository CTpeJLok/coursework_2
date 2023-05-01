package com.sport_objects.controllers;

import com.sport_objects.entities.Place;
import com.sport_objects.services.PlaceService;
import com.sport_objects.services.SportTypeService;
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
@RequestMapping("/place")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        List<Place> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "Места");

        return "place/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        Place place = new Place();

        model.addAttribute("obj", place);
        model.addAttribute("title", "Админ | Создание места");

        return "place/create";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("place/edit");
        Place place = service.get(id);
        mav.addObject("obj", place);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование команды " + id);

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") Place place) {
        service.save(place);
        return "redirect:/place";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/place";
    }

}
