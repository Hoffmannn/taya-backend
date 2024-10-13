package com.taya.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ResponseEntity<User>> createUser(User user) {
        return userRepository.save(user)
                .map(savedUser -> new ResponseEntity<>(savedUser, HttpStatus.CREATED));
    }

    public Flux<User> getUsers() {
        return this.userRepository.findAll();
    }

    public Mono<ResponseEntity<User>> getUserById(Integer id) {
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<User>> updateUser(User user) {
        return userRepository.findById(user.getId())
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setCity(user.getCity());
                    existingUser.setBirthDate(user.getBirthDate());
                    existingUser.setState(user.getState());
                    existingUser.setZipcode(user.getZipcode());
                    return userRepository.save(existingUser);
                })
                .map(updatedUser -> new ResponseEntity<>(updatedUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<String>> deleteUser(Integer id) {
        return userRepository.findById(id)
                .flatMap(existingUser ->
                        userRepository.deleteById(id)
                                .then(Mono.just(new ResponseEntity<>("User of id " + id + " deleted successfully", HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
