package com.example.sport_objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/admin/users")
    public String makeAdmin(Model model, @Param("searchKeyword") String searchKeyword) {
        List<User> list = null;

        if (searchKeyword != null)
            list = userService.allUsers(searchKeyword);
        else
            list = userService.allUsers();

        model.addAttribute("userList", list);
        model.addAttribute("title", "Админ | Пользователи");

        return "admin-users";
    }

    @RequestMapping("/admin/users/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("admin-edit-user");
        User user = userService.findUserById(id);

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
        System.out.println(user.getPassword());

        mav.addObject("user", user);

        mav.getModelMap().addAttribute("title", "Админ | Редактирование пользователя " + id);
        return mav;
    }

    @RequestMapping(value = "/admin/users/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        List<Role> newRoles = user.getRoles().stream().toList();

        for (Role newRole : newRoles) {
            if (newRole == null || (!newRole.toString().equals("ROLE_ADMIN") && !newRole.toString().equals("ROLE_USER"))) {
                return "redirect:/admin/users";
            }
        }

        String[] phone = user.getPhone().replace("-", "").split(" ");
        phone[1] = phone[1].replace("(", "").replace(")", "");
        user.setPhone(String.join("", phone));
        System.out.println(user.getPassword());

        userService.updateUser(user);
        return "redirect:/admin/users";
    }

}
