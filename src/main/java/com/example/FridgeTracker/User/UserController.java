package com.example.FridgeTracker.User;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @GetMapping("/getUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> getUser(){

        String message =  "HELLO";

        return ResponseEntity.ok()
        .header("Content-Type", "application/json").body("{\"message\": \"" + message + "\"}");

    }
    
}
