package pl.java.spring.web.project.clientappproject.login.controller;

public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}