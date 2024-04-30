package com.example.FridgeTracker.Storage;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/addStorage/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addStorageToUser(@RequestParam UUID id, @RequestBody Storage request){
        return storageService.addStorageToUser(id, request);
    }

    @PostMapping("/deleteStorage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteStorage(@RequestBody Storage request) {
        return storageService.deleteStorage(request);
    }


}
