package com.example.FridgeTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/api") // Change to user ?
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    //Register User endpoint **Subject to change**
    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addUser(@RequestBody User user){


        if(user != null){

            if (userRepository.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("User already exists");
            }

            //Hash and Set new password
            String hashedPasswordString = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPasswordString);

            //Import into db
            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Fail to register");
        }
    }

    //Login api endpoint
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UUID> loginUser(@RequestBody User loginUser, HttpServletRequest request){

        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null && passwordEncoder.matches(loginUser.getPassword(), user.getPassword())) {

            HttpSession session = request.getSession();
            session.setAttribute("userEmail", user.getEmail());
            
            return ResponseEntity.ok(user.getId());
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }


    //get user object endpoint
    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {       

      // can also use { @RequestHeader("email-tkn") String userEmail  }

        //Add a error check
        Optional<User> user = userRepository.findById(id);
        
        if(user != null){
            return ResponseEntity.ok(user.get()); 
        } else {
            return ResponseEntity.status(600).body(null); 
        }
    }

    @PostMapping("/updateUser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateUser(@RequestBody User Request){
        Optional<User> OptUser = userRepository.findById(Request.getId());
        if (OptUser.isPresent()){
            User user = OptUser.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(Request.getPassword(),user.getPassword())){
                if (Request.getFamilyName()!= ""){
                    user.setFamilyName(Request.getFamilyName());
                }
                if (Request.getEmail()!= ""){
                    user.setEmail(Request.getEmail());
                }
                userRepository.save(user);
                return ResponseEntity.ok("Password match.");
            }else{
                return ResponseEntity.ok("Password does not match.");
            }
         
        }

        return ResponseEntity.ok("The new user information is successfully updated.");
    }
    
    //Change passsword endpoint **Subject to change**
    @PostMapping("/change-password")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request, HttpServletRequest request1) {
        
        HttpSession session = request1.getSession();
        session.getAttribute("userEmail");

        User user = userRepository.findByEmail(request.getEmail());
    
            
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect current password");
        }
            
        String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(hashedNewPassword);
        userRepository.save(user);        
        return ResponseEntity.ok("Password changed successfully");
    }
    
}
