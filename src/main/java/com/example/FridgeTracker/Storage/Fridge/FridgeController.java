package com.example.FridgeTracker.Storage.Fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;
import java.util.Optional;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FridgeController {

    //T
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FridgeRepository fridgeRepository;


    @PostMapping("/addFridge")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addFridgeToUser(@RequestBody NewFridgeBody request){

        Optional<User> user = userRepository.findById(request.getUserID());

        if (user != null){
            Fridge fridge = new Fridge();

            fridge.setStorageName(request.getStorageName());
            fridge.setCapacity(request.getCapacity());

            fridge.setUser(user);

            fridgeRepository.save(fridge);

            return ResponseEntity.ok("Fridge added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }
    }
    
}
