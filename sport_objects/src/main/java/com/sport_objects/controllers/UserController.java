package com.sport_objects.controllers;

import com.sport_objects.entities.Role;
import com.sport_objects.entities.User;
import com.sport_objects.services.RoleService;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping({"", "/"})
    public String index(Model model, @Param("searchKeyword") String searchKeyword) {
        model.addAttribute("title", "Пользователи");
        model.addAttribute("searchKeyword", searchKeyword);

        List<User> list = null;

        if (searchKeyword != null)
            list = userService.findAll(searchKeyword);
        else
            list = userService.findAll();

        model.addAttribute("userList", list);

        return "user/index";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") Long id) {
        ModelAndView mav = null;

        if (userService.isExist(id)) {
            mav = new ModelAndView("user/edit");

            mav.getModelMap().addAttribute("title", "Редактирование пользователя " + id);

            User user = userService.findById(id);

            if (user.getPhone() != null) {
                String[] phone = user.getPhone().split("");
                StringBuilder html_phone = new StringBuilder();
                for (int i = 0; i < phone.length; i++) {
                    if (i == 1)
                        html_phone.append(phone[i]).append(" (");
                    else if (i == 4)
                        html_phone.append(phone[i]).append(") ");
                    else if (i == 7 || i == 9)
                        html_phone.append(phone[i]).append("-");
                    else
                        html_phone.append(phone[i]);
                }
                user.setPhone(html_phone.toString());
            }

            mav.addObject("user", user);

            List<Role> roles = roleService.findAll();
            mav.getModelMap().addAttribute("rolesList", roles);
        } else
            mav = new ModelAndView("redirect:/user");

        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/user";

        List<Role> roles = user.getRoles();
        for (Role role : roles)
            if (!roleService.isRoleExist(role.getName()))
                return "redirect:/user";

        String[] phone = user.getPhone().replace("-", "").split(" ");
        phone[1] = phone[1].replace("(", "").replace(")", "");
        user.setPhone(String.join("", phone));

        userService.tryUpdate(user);
        return "redirect:/user";
    }

}
