package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;
    private final UserService userService;

    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String userPage(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }


    @GetMapping("/user-create")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-create";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles") String[] roles) {

        Set<Role> rolesList = new HashSet<>();
        for (String role : roles) {
            rolesList.add(roleService.getByName("ROLE_" + role));
        }
        user.setRoles(rolesList);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "user-update";
    }
    @PostMapping("/user-update/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam(value = "roles") String[] roles) {
        Set<Role> rolesList = new HashSet<>();
        for (String role : roles) {
            rolesList.add(roleService.getByName("ROLE_" + role));
        }
        user.setRoles(rolesList);
        userService.updateUser(user);
        return "redirect:/admin";
    }
}

