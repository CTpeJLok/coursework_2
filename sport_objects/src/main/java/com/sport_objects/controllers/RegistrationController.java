package com.sport_objects.controllers;

import com.sport_objects.entities.User;
import com.sport_objects.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String registration(Model model) {
        model.addAttribute("useForm", new User());
        return "registr";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("useForm") @Valid User userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registr";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("Error", "Пароли не совпадают");
            return "registr";
        }

        String[] phone = userForm.getPhone().replace("-", "").split(" ");
        phone[1] = phone[1].replace("(", "").replace(")", "");
        userForm.setPhone(String.join("", phone));

        if (!userService.saveUser(userForm)) {
            model.addAttribute("Error", "Пользователь с таким именем уже существует");
            return "registr";
        }

        return "redirect:/login";
    }
    
}
