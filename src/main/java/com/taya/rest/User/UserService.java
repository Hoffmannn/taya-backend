package com.taya.rest.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> createUser(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id){
        return this.userRepository.findById(id);
    }

    public ResponseEntity<Object> updateUser(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());

        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User existingUser = userOptional.get();

        existingUser.setName(user.getName());
        existingUser.setCity(user.getCity());
        existingUser.setBirthDate(user.getBirthDate());
        existingUser.setState(user.getState());
        existingUser.setZipcode(user.getZipcode());

        userRepository.save(existingUser);

        return ResponseEntity.ok(existingUser);
    }

    public ResponseEntity<Object> deleteUser(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("User of id "+id+" deleted successfully");
    }
}
