package com.sport_objects.controllers;

import com.sport_objects.entities.Position;
import com.sport_objects.services.PositionService;
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
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Распределение мест");
        model.addAttribute("positionList", positionService.findAll());

        return "position/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание распределения мест");
        model.addAttribute("position", new Position());

        return "position/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (positionService.isExist(id)) {
            mav = new ModelAndView("position/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование распределения мест " + id);
            mav.addObject("position", positionService.get(id));
        } else
            mav = new ModelAndView("redirect:/position");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("position") Position position, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/position";

        try {
            positionService.save(position);
        } catch (Exception e) {

        }

        return "redirect:/position";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        positionService.del(id);
        return "redirect:/position";
    }

}
