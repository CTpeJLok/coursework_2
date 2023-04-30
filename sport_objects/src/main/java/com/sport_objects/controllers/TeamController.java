package com.sport_objects.controllers;

import com.sport_objects.entities.Team;
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
public class TeamController {

    @Autowired
    private TeamService service;

    @RequestMapping("/team")
    public String SportType(Model model, @Param("searchKeyword") String searchKeyword) {
        List<Team> list = null;

        if (searchKeyword != null)
            list = service.findAll(searchKeyword);
        else
            list = service.findAll();

        model.addAttribute("List", list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("title", "Команды");

        return "team";
    }

    @RequestMapping("/team/create")
    public String create(Model model) {
        Team team = new Team();
        model.addAttribute("obj", team);

        return "team-create";
    }

    @RequestMapping("/team/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("team-edit");
        Team team = service.get(id);
        mav.addObject("obj", team);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование команды " + id);
        return mav;
    }

    @RequestMapping(value = "/team/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") Team team) {
        service.save(team);
        return "redirect:/team";
    }

    @RequestMapping("/team/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        service.del(id);
        return "redirect:/team";
    }

}
