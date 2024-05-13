package com.example.FridgeTracker.User;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        User user = new UserBuilder()
            .setFamilyName(userDTO.getFamilyName())
            .setEmail(userDTO.getEmail())
            .setPassword(userDTO.getPassword())
            .setRank(userDTO.getRank())
            .setImageData(userDTO.getImageData())
            .withTimezone(userDTO.getTimezone())
            .withStorageEmpty(userDTO.isStorageEmpty())
            .withStorageFull(userDTO.isStorageFull())
            .withExpiryDate(userDTO.isExpiryDate())
            .build();
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
    public ResponseEntity<String> updateUser(@RequestBody UserDTO Request){
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
    public ResponseEntity<String> updateNotifications(@RequestBody UserDTO request){
        return userService.updateNotifications(request);
    }

    @PostMapping("/uploadFridgeAndFreezer/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadFridgeAndFreezer(@PathVariable UUID id){
        return userService.fillFridgeAndFreezer(id);
    }

    @PostMapping("/AdminChange/{adminID}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> uploadFridgeAndFreezer(@PathVariable UUID adminID, @RequestBody PasswordRequest request){
        return userService.adminAccessMethod(request, adminID);
    }

}
