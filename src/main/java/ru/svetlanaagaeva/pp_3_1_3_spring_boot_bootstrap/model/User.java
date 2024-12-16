package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "users_bootstrap")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
//    @Column(name = "username", unique = true, nullable = false)
//    private String username;
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "age")
    private Integer age;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_bootstrap_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
    }

    public User() {
    }

    public User(String name, String surname, String email, String password,Integer age, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
    }

//    public void setUsername(String username) {
//        this.email = username;
//    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "first_name")
//    private String name;
//    @Column(name = "last_name")
//    private String surname;
//    @Column(name = "age")
//    private int age;
//    //@Email(message = "Email should be valid")
////    @Column(name = "email", unique = true, nullable = false)
//    @Column
//    private String email;
//    private String password;
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "users_bootstrap_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    private Collection<Role> roles;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
//    }
//
//    public User() {
//    }
//
//    public User(String name, String surname, int age, String email, String password,  Collection<Role> roles) {
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    public User(String name, String surname, int age) {
//        this.name = name;
//        this.surname = surname;
//        this.age = age;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Collection<Role> getRoles() {
//        return roles;
//    }
//    public String getRole() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return String.join(" ", AuthorityUtils.authorityListToSet(getRoles()));
//    }
//    public void setRoles(Collection<Role> roles) {
//        this.roles = roles;
//    }
//
//   // public void addRole(Role role) {
////        this.roles.add(role);
////    }
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//
//}
