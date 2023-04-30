package com.sport_objects.controllers;

import com.sport_objects.entities.SportType;
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
public class SportTypeController {

    @Autowired
    private SportTypeService service;

    @RequestMapping("/sport-type")
    public String SportType(Model model, @Param("searchKeyword") String searchKeyword) {
        List<SportType> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "Виды спорта");

        return "sport-type";
    }

    @RequestMapping("/sport-type/create")
    public String create(Model model) {
        SportType sportType = new SportType();
        model.addAttribute("obj", sportType);

        return "sport-type-create";
    }

    @RequestMapping("/sport-type/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("sport-type-edit");
        SportType sportType = service.get(id);
        mav.addObject("obj", sportType);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование вида спорта " + id);
        return mav;
    }

    @RequestMapping(value = "/sport-type/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") SportType sportType) {
        service.save(sportType);
        return "redirect:/sport-type";
    }

    @RequestMapping("/sport-type/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/sport-type";
    }

}
