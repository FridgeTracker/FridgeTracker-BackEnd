package com.example.FridgeTracker.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addStorageToUser(@RequestBody StorageRequest request){
        return storageService.addStorageToUser(request);
    }

    @PostMapping("/deleteStorage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteStorage(@RequestBody Storage request) {
        return storageService.deleteStorage(request);
    }


}
