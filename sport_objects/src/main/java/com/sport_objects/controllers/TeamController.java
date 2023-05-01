package com.sport_objects.controllers;

import com.sport_objects.entities.SportType;
import com.sport_objects.entities.Team;
import com.sport_objects.services.SportTypeService;
import com.sport_objects.services.TeamService;
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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService service;

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        List<Team> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "Команды");

        return "team/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        Team team = new Team();
        List<SportType> sportTypes = sportTypeService.findAll();

        model.addAttribute("obj", team);
        model.addAttribute("sportTypes", sportTypes);
        model.addAttribute("title", "Админ | Создание команды");

        return "team/create";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("team/edit");
        Team team = service.get(id);
        mav.addObject("obj", team);

        List<SportType> sportTypes = sportTypeService.findAll();

        mav.getModelMap().addAttribute("title", "Админ | Редактирование команды " + id);
        mav.getModelMap().addAttribute("sportTypes", sportTypes);

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") Team team) {
        service.save(team);
        return "redirect:/team";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/team";
    }

}
