package com.example.FridgeTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
    

    @GetMapping("/getUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> getUser(HttpServletRequest request) {

        String userEmail = (String) request.getSession().getAttribute("userEmail");

        if (userEmail != null) {

            User user = userRepository.findByEmail(userEmail);
            
            if(user != null){
                return ResponseEntity.ok(user); // Return user if found
            }
        } 

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if user not found
        
    }
    
}
