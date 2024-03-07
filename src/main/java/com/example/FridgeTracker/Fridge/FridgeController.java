package com.example.FridgeTracker.Fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;
import java.util.List;


@RestController
public class FridgeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FridgeRepository fridgeRepository;

    @GetMapping
    public List<Fridge> getFridgesByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user != null){
            return fridgeRepository.findByOwner(user);
        } else {
            return null;
        }
    }
    
}
