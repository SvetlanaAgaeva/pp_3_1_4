package ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.model.Role;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.model.User;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping

    public String ListOfPeople(Model model) {
        List<User> allUsers = userService.getAllUsers();
        User userAuth = userService.getAuthUser();
        List<Role> allRoles = userService.getAllRoles();
        model.addAttribute("users", allUsers);
        model.addAttribute("user", userAuth);
        model.addAttribute("allRoles", userService.getAllRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

}