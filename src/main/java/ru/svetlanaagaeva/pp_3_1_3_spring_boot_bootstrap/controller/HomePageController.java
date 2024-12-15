package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String startPage() {
        return "login";
    }
}
