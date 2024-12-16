package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

//@Entity
//@Table(name = "bootstrap_roles")
//
//public class Role implements GrantedAuthority {
//
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//    @Column(name = "name")
//    private String name;
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users ;
//    public Role(Long id, String name, Set<User> users) {
//        this.id = id;
//        this.name = name;
//        this.users = users;
//    }
//
//    public Role() {
//    }
//    public Role(long id, String role) {
//        this.id = id;
//        this.name = role;
//    }
//
//    public Role(Long id) {
//        this.id = id;
//    }
//
//    @Override
//    public String getAuthority() {
//        return name;
//    }
//
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
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bootstrap_roles")

public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    @Override
    public String toString() {
        return name;
    }

    public Role() {
    }

    public Role(String role) {
        this.name = role;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}


