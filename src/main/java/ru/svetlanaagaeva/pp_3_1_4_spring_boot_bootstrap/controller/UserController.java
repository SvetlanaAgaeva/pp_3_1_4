package ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.model.User;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

    @RequestMapping("/user")
    public String showUserProfile() {
        return "user";
    }

}