package com.example.FridgeTracker.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
                User user = optUser.get();

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
