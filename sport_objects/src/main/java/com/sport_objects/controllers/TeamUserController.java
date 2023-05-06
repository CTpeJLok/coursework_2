package com.sport_objects.controllers;

import com.sport_objects.entities.TeamUser;
import com.sport_objects.entities.User;
import com.sport_objects.services.TeamService;
import com.sport_objects.services.TeamUserService;
import com.sport_objects.services.UserService;
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
@RequestMapping("/team-user")
public class TeamUserController {

    @Autowired
    private TeamUserService teamUserService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping("{team_id}")
    public String index(Model model, @PathVariable(name = "team_id") Long team_id,
                        @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Пользователи команды " + team_id);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("team_id", team_id);

        List<TeamUser> teamUserList = teamUserService.findByTeamId(team_id, searchKeyword);
        model.addAttribute("teamUserList", teamUserList);

        return "team-user/index";
    }

    @RequestMapping("{team_id}/create")
    public ModelAndView create(@PathVariable(name = "team_id") Long team_id) {
        ModelAndView mav = new ModelAndView("team-user/create-edit");
        mav.getModelMap().addAttribute("title", "Добавить пользователя команде " + team_id);
        mav.getModelMap().addAttribute("team_id", team_id);
        mav.getModelMap().addAttribute("create", true);


        TeamUser teamUser = new TeamUser(teamService.get(team_id));
        mav.addObject("teamUser", teamUser);

        List<User> userList = userService.findAll();
        mav.getModelMap().addAttribute("userList", userList);

        return mav;
    }

    @RequestMapping("{team_id}/edit/{team_user_id}")
    public ModelAndView edit(@PathVariable(name = "team_id") Long team_id,
                             @PathVariable(name = "team_user_id") Long team_user_id) {
        ModelAndView mav = null;

        if (teamUserService.isExist(team_user_id)) {
            mav = new ModelAndView("team-user/create-edit");
            mav.getModelMap().addAttribute("title",
                    "Редактирование пользователя команды " + team_id);
            mav.getModelMap().addAttribute("team_id", team_id);
            mav.getModelMap().addAttribute("create", false);

            TeamUser teamUser = teamUserService.findById(team_user_id);
            mav.addObject("teamUser", teamUser);

        } else
            mav = new ModelAndView("redirect:/team-user/" + team_id);

        return mav;
    }

    @RequestMapping(value = "{team_id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("teamUser") TeamUser teamUser, @PathVariable(name = "team_id") Long team_id,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/team-user/" + team_id;

        teamUserService.save(teamUser);
        return "redirect:/team-user/" + team_id;
    }

    @RequestMapping("{team_id}/delete/{team_user_id}")
    public String delete(@PathVariable(name = "team_id") Long team_id,
                         @PathVariable(name = "team_user_id") Long team_user_id) {
        teamUserService.del(team_user_id);
        return "redirect:/team-user/" + team_id;
    }

}
