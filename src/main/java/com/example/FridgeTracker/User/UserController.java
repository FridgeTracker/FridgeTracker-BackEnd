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
import java.util.List;





@RestController
@RequestMapping("/api") // Change to user ?
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //Register User endpoint **Subject to change**
    @PostMapping("/register")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //Login api endpoint
    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser){
        return userService.loginUser(loginUser);
    }


    //get user object endpoint
    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {   
        return userService.getUser(id);
    }


    //update Account Info
    @PostMapping("/updateUser")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateUser(@RequestBody User Request){
        return userService.updateUser(Request);
    }


    //Change Password
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


    @GetMapping("/getUsers")
    @CrossOrigin(origins = "*")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/updateNotifications")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateNotifications(@RequestBody User request){
        return userService.updateNotifications(request);
    }

}
