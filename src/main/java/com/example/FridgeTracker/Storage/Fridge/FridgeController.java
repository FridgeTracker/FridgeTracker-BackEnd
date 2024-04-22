package com.example.FridgeTracker.Storage.Fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FridgeController {

    //T
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FridgeRepository fridgeRepository;

    private final FridgeService fridgeService;

    @Autowired
    public FridgeController(FridgeService fridgeService) {
        this.fridgeService = fridgeService;
    }



    @PostMapping("/addFridge")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addFridgeToUser(@RequestBody NewFridgeBody request){
        return fridgeService.addFridgeToUser(request);
    }
        
    @PostMapping("/deleteFridge/{fridgeId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteFridge(@PathVariable UUID fridgeId) {
        return ResponseEntity.status(HttpStatus.OK).body(fridgeService.deleteFridge(fridgeId));
    }
    
}
