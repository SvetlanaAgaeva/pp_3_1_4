package ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomePageRestController {
    @GetMapping("/")
     // это на всякий случай оставила
//    public ResponseEntity<Void> redirectToLogin() {
//        return ResponseEntity.status(HttpStatus.FOUND) // 302 Redirect
//                .header("Location", "/login.html") // Перенаправляет на логин
//                .build();
//    }
    public RedirectView redirectToLogin() {
        return new RedirectView("/login.html");
    }
}