package ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.model.Role;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<Role> getAllRoles();

    void saveUser(User user);

    void updateUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getAuthUser();
}
