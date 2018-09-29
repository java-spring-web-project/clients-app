package pl.java.spring.web.project.clientappproject.login.controller;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}