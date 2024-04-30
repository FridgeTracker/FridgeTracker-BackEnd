package com.example.FridgeTracker.Storage;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.Storage.Storage.StorageType;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

@Service
public class StorageService {

    private final UserRepository userRepository;

     public StorageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> addStorageToUser(StorageRequest request){
        Optional<User> userOptional = userRepository.findById(request.getUserID());
        
        if (userOptional.isPresent()) {

            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = new FreezerFactory();
            } else{
                storageFactory = new FridgeFactory();
            }

            Storage storage = storageFactory.createStorage(userOptional);
            storage.setType(request.getType());
            storage.setCapacity(request.getCapacity());
            storage.setStorageName(request.getStorageName());
            storage.setId(request.getId());
            storageFactory.save(storage);

            return ResponseEntity.ok("Storage added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    public ResponseEntity<String> deleteStorage(Storage request){

        try {
            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = new FreezerFactory();
            } else{
                storageFactory = new FridgeFactory();
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
