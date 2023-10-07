package ru.yandex.practicum.catsgram.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Map<String, User> users = new HashMap<>();

    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        String email = user.getEmail();
        if (email == null || email.isEmpty()) {
            throw new InvalidEmailException("Не указан адрес электронной почты.");
        }
        if (users.containsKey(email)) {
            throw new UserAlreadyExistException(String.format("Пользователь с электронным адресом %s уже" +
                    "существует", email));
        }
        users.put(email, user);
        return user;
    }

    @PutMapping
    public User saveUser(@RequestBody User user) {
        String email = user.getEmail();
        if (email == null || email.isEmpty()) {
            throw new InvalidEmailException("Не указан адрес электронной почты.");
        }
        users.put(email, user);
        return user;
    }
}
