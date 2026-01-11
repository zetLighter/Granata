package com.lighter.granate.controller;

import com.lighter.granate.dto.CreateUserDTO;
import com.lighter.granate.dto.UpdateUserDTO;
import com.lighter.granate.entity.User;
import com.lighter.granate.repository.UserRepository;
import com.lighter.granate.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class UserController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/user")
    public void addUser(@RequestBody CreateUserDTO userDto) {
        log.info(userService.createUser(userDto));

    }
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.readUsers();
    }
    @GetMapping("/user")
    public User getUser(@RequestParam int id) {
        return userService.readUser(id);
    }
    @DeleteMapping("/admin/user")
    public void removeUser(HttpServletRequest request, @RequestParam int id) {
        userService.deleteUser(id);
    }
    @PutMapping("/admin/user")
    public String editUser(@RequestBody UpdateUserDTO userDto) {
        return userService.updateUser(userDto);
    }
}
