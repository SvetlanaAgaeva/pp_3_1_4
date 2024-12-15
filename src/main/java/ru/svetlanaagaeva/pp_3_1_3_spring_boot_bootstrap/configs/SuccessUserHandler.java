package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.configs;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;


@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {

//        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
//
//        if (roles.contains("ROLE_ADMIN")) {
//            response.sendRedirect("/admin");
//        } else if (roles.contains("ROLE_USER")) {
//            response.sendRedirect("/user");
//        } else {
//            response.sendRedirect("/");
//        }
//

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                Authentication authentication) throws IOException, ServletException {

            if (authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/user");
            }
    }

}