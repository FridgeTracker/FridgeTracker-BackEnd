package com.example.FridgeTracker.Storage;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Storage.Storage.StorageType;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StorageController {

    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/addStorage")
    public ResponseEntity<String> addStorageToUser(@RequestBody StorageRequest request){
        return storageService.addStorageToUser(request);
    }

    @PostMapping("/deleteStorage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteStorage(@RequestBody StorageRequest request) {
        return storageService.deleteStorage(request);
    }


}
