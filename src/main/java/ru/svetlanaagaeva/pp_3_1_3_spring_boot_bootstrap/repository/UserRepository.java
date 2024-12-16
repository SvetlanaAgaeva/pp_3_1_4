package ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.svetlanaagaeva.pp_3_1_3_spring_boot_bootstrap.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmailIgnoreCase(String email);
//User findByName(String name);
}