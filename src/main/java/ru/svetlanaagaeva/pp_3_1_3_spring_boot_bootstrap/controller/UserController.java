package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.User;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping

    public String showUserPage(Model model) {
        User userAuth = userService.getAuthUser();
        if (userAuth == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", userAuth);
        return "user";
    }

    @GetMapping("/{id}")

    public String showUserProfile(@PathVariable("id") Long id, Model model) {
        try {
            User user = userService.getUserById(id);
            model.addAttribute("user", user);

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "user";
    }


}