package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.Role;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    List<Role> getAllRoles();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getAuthUser();

}