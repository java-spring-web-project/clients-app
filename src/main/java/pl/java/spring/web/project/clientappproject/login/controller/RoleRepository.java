package pl.java.spring.web.project.clientappproject.login.controller;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}