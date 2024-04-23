package com.example.FridgeTracker.Storage.Freezer;

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

import com.example.FridgeTracker.User.UserRepository;

import java.util.UUID;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FreezerController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreezerRepository freezerRepository;
    
    private final FreezerService freezerService;

    @Autowired
    public FreezerController(FreezerService freezerService){
        this.freezerService = freezerService;
    }


    @PostMapping("/addFreezer")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addFridgeToUser(@RequestBody NewFreezerBody request){
        return freezerService.addFridgeToUser(request);
    
    }

    @PostMapping("/deleteFreezer/{freezerId}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteFridge(@PathVariable UUID freezerId) {
        return ResponseEntity.status(HttpStatus.OK).body(freezerService.deleteFridge(freezerId));
    }
    
}
