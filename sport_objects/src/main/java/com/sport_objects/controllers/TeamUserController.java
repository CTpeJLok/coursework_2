package com.sport_objects.controllers;

import com.sport_objects.entities.TeamUser;
import com.sport_objects.entities.User;
import com.sport_objects.services.TeamService;
import com.sport_objects.services.TeamUserService;
import com.sport_objects.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/team-user")
public class TeamUserController {

    @Autowired
    private TeamUserService service;

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @RequestMapping("{id}")
    public String index(Model model, @PathVariable(name = "id") Long id) {
        List<TeamUser> teamUsers = service.findByTeamID(id);

        model.addAttribute("teamId", id);
        model.addAttribute("List", teamUsers);
        model.addAttribute("title", "Админ | Пользователи команды " + id);

        return "team-user/index";
    }

    @RequestMapping("{id}/add")
    public ModelAndView addUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("team-user/add");
        TeamUser teamUser = new TeamUser(teamService.get(id));
        mav.addObject("obj", teamUser);

        List<User> users = userService.allUsers();
        List<Long> usersID = new ArrayList<>();
        for (User u : users)
            usersID.add(u.getId());

        List<TeamUser> teamUsers = service.findByTeamID(id);
        for (TeamUser tu : teamUsers) {
            long current_id = tu.getUser().getId();

            if (usersID.contains(current_id)) {
                int index = usersID.indexOf(current_id);
                users.remove(index);
                usersID.remove(index);
            }
        }

        mav.getModelMap().addAttribute("teamId", id);
        mav.getModelMap().addAttribute("title", "Админ | Добавить пользователя команды");
        mav.getModelMap().addAttribute("users", users);

        return mav;
    }

    @RequestMapping("{id}/edit/{user_id}")
    public ModelAndView addUser(@PathVariable(name = "id") Long id, @PathVariable(name = "user_id") Long user_id) {
        ModelAndView mav = new ModelAndView("team-user/edit");
        List<TeamUser> teamUsers = service.findByTeamID(id);
        for (TeamUser tu : teamUsers) {
            if (tu.getUser().getId() == user_id) {
                mav.addObject("obj", tu);
                break;
            }
        }

        mav.getModelMap().addAttribute("teamId", id);
        mav.getModelMap().addAttribute("title", "Админ | Редактирование пользователя команды");

        return mav;
    }

    @RequestMapping(value = "{id}/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("obj") TeamUser teamUser, @PathVariable(name = "id") Long id) {
        service.save(teamUser);

        return "redirect:/team-user/" + id;
    }

    @RequestMapping("{id}/delete/{user_id}")
    public String delete(@PathVariable(name = "id") Long id, @PathVariable(name = "user_id") Long user_id) {
        List<TeamUser> teamUsers = service.findByTeamID(id);
        for (TeamUser tu : teamUsers) {
            if (tu.getUser().getId() == user_id) {
                service.del(tu.getId());
                break;
            }
        }

        return "redirect:/team-user/" + id;
    }

}
