package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.Role;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.User;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.repository.RoleRepository;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.repository.UserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {

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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user == null) {
            throw new UsernameNotFoundException("Incorrect email or password");

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
                Set<Role> roles = new HashSet<>();
                roles.add(userRoleOptional.get());
                user.setRoles(roles);
                //user.setRoles(Collections.singleton(userRoleOptional.get()));
            } else {
                throw new RuntimeException("Role USER not found");
            }
        }
        userRepository.save(user);
    }


    @Override
    public void updateUser(User user) {

        User updatedUser = getUserById(user.getId());
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setEmail(user.getEmail());
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
//    public User getAuthUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return (User) authentication.getPrincipal();  // Получаем пользователя из контекста безопасности
//    }


    @Override
    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmailIgnoreCase(auth.getName());
    }

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByName(username);  // Вместо findByUsername, используем findByEmail
//        if (user == null) {
//            throw new UsernameNotFoundException("Incorrect username or password");
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), user.getAuthorities());
//    }
//
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public List<Role> getAllRoles() {
//        return roleRepository.findAll();
//    }
//
//
//    @Override
//    public User getById(Long id) {
//        return userRepository.getById( id);
//               //
//
//    }
//
//
////    @Override
////    public void saveUser(User user) {
////        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
////        if (user.getRoles() == null || user.getRoles().isEmpty()) {
////            Optional<Role> userRoleOptional = roleRepository.findByName("USER");
////            if (userRoleOptional.isPresent()) {
////                user.setRoles(Collections.singleton(userRoleOptional.get()));
////            } else {
////                throw new RuntimeException("Role USER not found");
////            }
////        }
////        userRepository.save(user);
////    }
//
//    @Override
//    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
////    @Override
////    public void updateUser(User user) {
////
////        User updatedUser = getUserById(user.getId());
////        updatedUser.setName(user.getName());
////        updatedUser.setSurname(user.getSurname());
////        updatedUser.setEmail(user.getEmail());
////        updatedUser.setPassword((bCryptPasswordEncoder.encode(user.getPassword())));
////        updatedUser.setRoles(user.getRoles());
////        userRepository.save(updatedUser);
////    }
//@Override
//public void updateUser(User user) {
//    userRepository.save(user);
//}
//
//    @Override
//    public void deleteById(Long id) {
//        User user = userRepository.findById(id).get();
//        user.setRoles(null);
//       // userRepository.save(user);
//        userRepository.deleteById(id);
//
//    }
//
//    @Override
//    public User getAuthUser() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return userRepository.findByName(auth.getName());
//    }
//    @Override
//    public Role getName(){ return getName();}
}
