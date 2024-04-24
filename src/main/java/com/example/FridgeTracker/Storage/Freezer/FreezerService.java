package com.example.FridgeTracker.Storage.Freezer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.Storage.StorageRequest;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class FreezerService {
    private final FreezerRepository freezerRepository;
    private final UserRepository userRepository;

    @Autowired
    public FreezerService(FreezerRepository freezerRepository,UserRepository userRepository){

        this.freezerRepository = freezerRepository;
        this.userRepository = userRepository;
    }


    public ResponseEntity<String> addFridgeToUser(StorageRequest request){

        Optional<User> user = userRepository.findById(request.getUserID());

        if (user != null){
            Freezer freezer = new Freezer();

            freezer.setStorageName(request.getStorageName());
            freezer.setCapacity(request.getCapacity());
            freezer.setType("Freezer");

            freezer.setUser(user);

            freezerRepository.save(freezer);

            return ResponseEntity.ok("Freezer added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add freezer");
        }
    }


    @Transactional
    public String deleteFridge(UUID freezerID) {
        Optional<Freezer> optionalFreezer = freezerRepository.findById(freezerID);
        if (optionalFreezer.isPresent()) {
            Freezer freezer = optionalFreezer.get();
            freezerRepository.delete(freezer);
            return "Successfully deleted Freezer";
        } else {
            return "Failed to delete Freezer";
        }
    }
    
}
