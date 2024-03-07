package com.example.FridgeTracker.Fridge;

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
// import java.util.List;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class FridgeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FridgeRepository fridgeRepository;

   /* @GetMapping
    public List<Fridge> getFridgesByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user != null){
            return fridgeRepository.findByOwner(user);
        } else {
            return null;
        }
    }*/


    @PostMapping("/addFridge")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> addFridgeToUser(@RequestBody NewFridgeBody request){

        User user = userRepository.findByEmail(request.getUserEmail());

        if (user != null){
            Fridge fridge = new Fridge();

            fridge.setFridgeName(request.getFridgeName());
            fridge.setCapacity(request.getCapacity());

            fridge.setOwner(user);

            fridgeRepository.save(fridge);

            return ResponseEntity.ok("Fridge added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add fridge");
        }
    }
    
}
