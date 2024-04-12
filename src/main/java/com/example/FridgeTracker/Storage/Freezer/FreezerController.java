package com.example.FridgeTracker.Storage.Freezer;

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
public class FreezerController {

    //T
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FreezerRepository freezerRepository;


    @PostMapping("/addFreezer")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addFridgeToUser(@RequestBody NewFreezerBody request){

        Optional<User> user = userRepository.findById(request.getUserID());

        if (user != null){
            Freezer freezer = new Freezer();

            freezer.setStorageName(request.getStorageName());
            freezer.setCapacity(request.getCapacity());

            freezer.setOwner(user);

            freezerRepository.save(freezer);

            return ResponseEntity.ok("Freezer added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add freezer");
        }
    }
    
}
