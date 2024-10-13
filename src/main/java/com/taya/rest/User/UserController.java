package com.taya.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Flux<User> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> getUserById(@PathVariable Integer id) {
        return this.userService.getUserById(id);
    }

    @PostMapping("/")
    public Mono<ResponseEntity<User>> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/")
    public Mono<ResponseEntity<User>> updateUserById(@RequestBody User updatedUser) {
        return this.userService.updateUser(updatedUser);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> deleteUserById(@PathVariable Integer id) {
        return this.userService.deleteUser(id);
    }
}
