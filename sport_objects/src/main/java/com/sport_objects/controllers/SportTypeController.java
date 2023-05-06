package com.sport_objects.controllers;

import com.sport_objects.entities.SportType;
import com.sport_objects.services.SportTypeService;
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

import java.util.List;

@Controller
@RequestMapping("/sport-type")
public class SportTypeController {

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Виды спорта");
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("sportTypeList", sportTypeService.findAll(searchKeyword));

        return "sport-type/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание вида спорта");
        model.addAttribute("sportType", new SportType());

        return "sport-type/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (sportTypeService.isExist(id)) {
            mav = new ModelAndView("sport-type/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование вида спорта " + id);
            mav.addObject("sportType", sportTypeService.get(id));
        } else
            mav = new ModelAndView("redirect:/sport-type");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("sportType") SportType sportType, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/sport-type";

        sportTypeService.save(sportType);
        return "redirect:/sport-type";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        sportTypeService.del(id);
        return "redirect:/sport-type";
    }

}
