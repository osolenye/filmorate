package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping("/users")
    public List<User> getUsers() {
        return users;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) throws Exception {
        validateUser(user);
        users.add(user);
        log.info("A user has been created");
        return user;
    }

    @PutMapping("/users")
    public User putUser(@RequestBody User user) throws Exception {
        validateUser(user);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                users.set(i, user);
                log.info("A user has been changed");
                return user;
            }
        }
        users.add(user);
        log.info("A user has been created");
        return user;
    }

    public void validateUser(User user) throws Exception {
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.warn("Validate exception");
            throw new ValidationException("Email can't be empty and must contain '@'.");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.warn("Validate exception");
            throw new ValidationException("Login can't be empty and must not contain spaces.");
        }
        if (user.getReleaseDate() != null && user.getReleaseDate().isAfter(LocalDateTime.now())) {
            log.warn("Validate exception");
            throw new ValidationException("Birthday can't be in the future.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            log.warn("Validate exception");
            user.setName(user.getLogin());
        }
    }
}
