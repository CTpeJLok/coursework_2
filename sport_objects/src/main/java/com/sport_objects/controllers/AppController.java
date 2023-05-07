package com.sport_objects.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

    @RequestMapping({"", "/"})
    public String HomePage(Model model) {
        model.addAttribute("title", "Об авторе");

        return "index";
    }

}
