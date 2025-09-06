package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ValidationException("Email can't be empty and must contain '@'.");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            throw new ValidationException("Login can't be empty and must not contain spaces.");
        }
        if (user.getReleaseDate() != null && user.getReleaseDate().isAfter(LocalDateTime.now())) {
            throw new ValidationException("Birthday can't be in the future.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        users.add(user);
        return user;
    }

    @PutMapping("/users")
    public User putUser(@RequestBody User user) throws Exception {
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            throw new ValidationException("Email can't be empty and must contain '@'.");
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            throw new ValidationException("Login can't be empty and must not contain spaces.");
        }
        if (user.getReleaseDate() != null && user.getReleaseDate().isAfter(LocalDateTime.now())) {
            throw new ValidationException("Birthday can't be in the future.");
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                users.set(i, user);
                return user;
            }
        }
        users.add(user);
        return user;
    }
}
