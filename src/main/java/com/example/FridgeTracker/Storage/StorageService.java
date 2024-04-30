package com.example.FridgeTracker.Storage;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.Storage.Storage.StorageType;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

@Service
public class StorageService {

    private final UserRepository userRepository;
    private final FridgeFactory fridgeFactory;
    private final FreezerFactory freezerFactory;

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    public StorageService(UserRepository userRepository, FridgeFactory fridgeFactory, FreezerFactory freezerFactory) {
        this.userRepository = userRepository;
        this.fridgeFactory = fridgeFactory;
        this.freezerFactory = freezerFactory;
    }

    public ResponseEntity<String> addStorageToUser(StorageRequest request){
        Optional<User> userOptional = userRepository.findById(request.getUserID());
        
        if (userOptional.isPresent()) {

            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = freezerFactory;
            } else{
                storageFactory = fridgeFactory;
            }

            Storage storage = storageFactory.createStorage(userOptional);
            logger.info("Hello there I am here haha",storage);
            storage.setType(request.getType());
            storage.setCapacity(request.getCapacity());
            storage.setStorageName(request.getStorageName());
            storageFactory.save(storage);

            return ResponseEntity.ok("Storage added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<String> deleteStorage(StorageRequest request){

        try {
            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = freezerFactory;
            } else{
                storageFactory = fridgeFactory;
            }

            Storage storage = storageFactory.createStorage(Optional.empty());
            storage.setId(request.getId());
            storageFactory.delete(storage);
            
            return ResponseEntity.ok("Storage deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete storage");
        }
       
    }
}
