package com.example.FridgeTracker.Storage.Fridge;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;


@Service
public class FridgeService {
    private final FridgeRepository fridgeRepository;
    private final UserRepository userRepository;

    @Autowired
    public FridgeService(FridgeRepository fridgeRepository,UserRepository userRepository){
        this.fridgeRepository = fridgeRepository;
        this.userRepository = userRepository;
    }
   


    public ResponseEntity<String> addFridgeToUser(NewFridgeBody request){

        Optional<User> userOptional = userRepository.findById(request.getUserID());

        if (userOptional.isPresent()) { // Check if user is present

            Fridge fridge = new Fridge();

            fridge.setStorageName(request.getStorageName());
            fridge.setCapacity(request.getCapacity());

            fridge.setUser(userOptional);

            fridgeRepository.save(fridge);

            return ResponseEntity.ok("Fridge added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }


    
}
}
