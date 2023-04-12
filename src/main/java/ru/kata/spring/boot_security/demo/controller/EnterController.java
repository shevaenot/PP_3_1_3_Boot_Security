package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class EnterController {

    @GetMapping(value = "/enter")
    public String pageForEnter(Model model) {
        model.addAttribute("logins", new User());
        return "enter";
    }

}
