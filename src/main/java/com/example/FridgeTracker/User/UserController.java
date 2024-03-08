package com.example.FridgeTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    //Login api endpoint
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Long> loginUser(@RequestBody User loginUser, HttpServletRequest request){

        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("userEmail", user.getEmail());
            
            return ResponseEntity.ok(user.getId());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUser(@PathVariable Long id) {       

      // can also use { @RequestHeader("email-tkn") String userEmail  }

        User user = userRepository.findById(id).orElse(null);
        
        if(user != null){
            return ResponseEntity.ok(user); 
        } else {
            return ResponseEntity.status(600).body(null); 
        }
    }
    
}
