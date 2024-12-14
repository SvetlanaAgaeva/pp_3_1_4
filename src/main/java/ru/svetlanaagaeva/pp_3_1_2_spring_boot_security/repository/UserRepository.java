package ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.svetlanaagaeva.pp_3_1_2_spring_boot_security.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
}