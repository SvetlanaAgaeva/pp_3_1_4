package ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.service;

//import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.model.Role;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.model.User;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.repository.RoleRepository;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("Incorrect username or password");

        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with such ID doesn't exist."));

    }


    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Optional<Role> userRoleOptional = roleRepository.findByName("ROLE_USER");
            if (userRoleOptional.isPresent()) {
                user.setRoles(Collections.singleton(userRoleOptional.get()));
            } else {
                throw new RuntimeException("Role ROLE_USER not found");
            }
        }
        userRepository.save(user);
    }


    @Override
    public void updateUser(User user) {

        User updatedUser = getUserById(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
        updatedUser.setRoles(user.getRoles());
        userRepository.save(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).get();
        user.setRoles(null);
        userRepository.save(user);
        userRepository.deleteById(id);

    }

    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsernameIgnoreCase(auth.getName());
    }
}
