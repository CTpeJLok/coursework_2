package com.sport_objects.controllers;

import com.sport_objects.entities.HelpfulType;
import com.sport_objects.services.HelpfulTypeService;
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
@RequestMapping("/helpful-type")
public class HelpfulTypeController {

    @Autowired
    private HelpfulTypeService service;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        List<HelpfulType> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "Полезные категории");

        return "helpful-type/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        HelpfulType helpfulType = new HelpfulType();

        model.addAttribute("obj", helpfulType);
        model.addAttribute("title", "Админ | Создание полезной категории");

        return "helpful-type/create";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("helpful-type/edit");
        HelpfulType helpfulType = service.get(id);
        mav.addObject("obj", helpfulType);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование полезной категории " + id);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") HelpfulType helpfulType) {
        service.save(helpfulType);
        return "redirect:/helpful-type";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/helpful-type";
    }

}
