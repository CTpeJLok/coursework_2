package com.sport_objects.controllers;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.services.HelpfulTypeService;
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
@RequestMapping("/helpful-type")
public class HelpfulTypeController {

    @Autowired
    private HelpfulTypeService helpfulTypeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Полезные категории");
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("helpfulTypeList", helpfulTypeService.findAll(searchKeyword));

        return "helpful-type/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание полезной категории");
        model.addAttribute("helpfulType", new HelpfulType());

        return "helpful-type/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (helpfulTypeService.isExist(id)) {
            mav = new ModelAndView("helpful-type/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование полезной категории " + id);
            mav.addObject("helpfulType", helpfulTypeService.get(id));
        } else
            mav = new ModelAndView("redirect:/helpful-type");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("helpfulType") HelpfulType helpfulType, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/helpful-type";

        helpfulTypeService.save(helpfulType);
        return "redirect:/helpful-type";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        helpfulTypeService.del(id);
        return "redirect:/helpful-type";
    }

}
