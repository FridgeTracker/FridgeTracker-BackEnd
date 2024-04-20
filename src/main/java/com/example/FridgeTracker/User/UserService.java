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
