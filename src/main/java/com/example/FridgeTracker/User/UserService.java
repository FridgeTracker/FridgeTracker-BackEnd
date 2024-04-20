package com.example.FridgeTracker.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.ZoneId;



@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    

    //REGISTER USER ENDPOINT **SUBJECT TO CHANGE**
    public ResponseEntity<String> addUser(User user){
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

    //LOGIN API ENDPOINT
    public ResponseEntity<?> loginUser(User loginUser){
        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user != null){
            if(passwordEncoder.matches(loginUser.getPassword(), user.getPassword())){
                return ResponseEntity.ok(user.getId());
            }
            return ResponseEntity.status(999).body("Passwords don't match");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found");

    }


    //GET USER OBJECT ENDPOINT
    public ResponseEntity<User> getUser(UUID id){

      // can also use { @RequestHeader("email-tkn") String userEmail  }
        //Add a error check
        Optional<User> user = userRepository.findById(id);
        if(user != null){
            return ResponseEntity.ok(user.get()); 
        } else {
            return ResponseEntity.status(600).body(null); 
        }

    }

    //UPDATE USER ACCOUNT INFO
    public ResponseEntity<String> updateUser(User Request){

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

    //CHANGE THE PASSWORD
    public ResponseEntity<String> changePassword(PasswordRequest request){

        Optional<User> optUser = userRepository.findById(request.getId());

            if (optUser.isPresent()) {
                User user = optUser.get()
                if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                    String newPassword = request.getNewPw();
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return ResponseEntity.ok("Password changed successfully.");
                
                } else {
                    return ResponseEntity.ok("Current password is incorrect.");
                }
            }
        return ResponseEntity.ok("User not found.");
    }


    // GET TIMEZONES
    public List<String> getTimeZones(){
        return ZoneId.getAvailableZoneIds()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }


    
}
