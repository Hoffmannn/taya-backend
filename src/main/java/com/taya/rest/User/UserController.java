package com.taya.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/")
    public ResponseEntity<Object> updateUserById(@RequestBody User updatedUser){
        return this.userService.updateUser(updatedUser);
    }



    @GetMapping("/")
    public List<User> getUsers(){
        return this.userService.getUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id){
        return this.userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Integer id){
        return this.userService.deleteUser(id);
    }
}

