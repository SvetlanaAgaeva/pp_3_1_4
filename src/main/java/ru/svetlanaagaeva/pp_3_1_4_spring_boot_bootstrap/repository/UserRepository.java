package ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByEmailIgnoreCase(String email);

}