package ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.svetlanaagaeva.pp_3_1_4_spring_boot_bootstrap.model.Role;

import java.util.Optional;
//@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
