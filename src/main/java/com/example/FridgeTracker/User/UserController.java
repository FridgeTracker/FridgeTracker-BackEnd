package com.example.FridgeTracker.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;




@RestController
@RequestMapping("/api") // Change to user ?
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


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
    public ResponseEntity<?> loginUser(@RequestBody User loginUser){

        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null){

            if(passwordEncoder.matches(loginUser.getPassword(), user.getPassword())){

                return ResponseEntity.ok(user.getId());
            }
            return ResponseEntity.status(999).body("Passwords don't match");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");
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


            if(Request.getImageData() != null){
                user.setImageData(Request.getImageData());
                userRepository.save(user);
                return ResponseEntity.ok("Profile Picture Saved");
            }
     
            if (passwordEncoder.matches(Request.getPassword(),user.getPassword())){

                if (Request.getFamilyName()!= ""){
                    user.setFamilyName(Request.getFamilyName());
                }
                if (Request.getEmail()!= ""){
                    user.setEmail(Request.getEmail());
                }
                if(Request.getTimezone() != ""){
                    user.setTimezone(Request.getTimezone());
                }
                
                userRepository.save(user);
                return ResponseEntity.ok("User information updated.");
            }else{
                return ResponseEntity.ok("Password does not match.");
            }
         
        }

        return ResponseEntity.ok("The new user information is successfully updated.");
    }

    @PostMapping("/changePw")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> changePassword(@RequestBody PasswordRequest request) {
        return userService.changePassword(request);
    }


    @GetMapping("/timezone")
    @CrossOrigin(origins = "*")
    public List<String> getTimeZoneList() {
        return userService.getTimeZones();
    }

}
