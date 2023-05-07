package com.sport_objects.controllers;

import com.sport_objects.entities.Team;
import com.sport_objects.services.SportTypeService;
import com.sport_objects.services.TeamService;
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
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private SportTypeService sportTypeService;

    @RequestMapping("")
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Команды");
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("teamList", teamService.findAll(searchKeyword));

        return "team/index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Создание команды");
        model.addAttribute("create", true);
        model.addAttribute("team", new Team());
        model.addAttribute("sportTypes", sportTypeService.findAll(null));

        return "team/create-edit";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (teamService.isExist(id)) {
            mav = new ModelAndView("team/create-edit");
            mav.getModelMap().addAttribute("title", "Редактирование команды " + id);
            mav.getModelMap().addAttribute("create", false);
            mav.addObject("team", teamService.get(id));
            mav.getModelMap().addAttribute("sportTypes", sportTypeService.findAll(null));
        } else
            mav = new ModelAndView("redirect:/team");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("team") Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/team";

        try {
            teamService.save(team);
        } catch (Exception e) {

        }

        return "redirect:/team";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        teamService.del(id);
        return "redirect:/team";
    }

}
