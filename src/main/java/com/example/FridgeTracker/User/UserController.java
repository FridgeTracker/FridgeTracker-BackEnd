package com.example.FridgeTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


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

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser, HttpServletRequest request){

        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", user.getEmail());
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }

    @GetMapping("/getUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        
        User user = userRepository.findByEmail(userEmail);
        
        if(user != null){
            return ResponseEntity.ok(user); 
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
    }
    
}
