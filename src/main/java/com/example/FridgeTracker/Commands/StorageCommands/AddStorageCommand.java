package com.example.FridgeTracker.Commands.StorageCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Storage.StorageType;
import com.example.FridgeTracker.Storage.StorageRequest;
import com.example.FridgeTracker.Storage.FactoryMethod.FreezerFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.FridgeFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.StorageFactory;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

public class AddStorageCommand implements Command{

    private UserRepository userRepository;
    private StorageRequest request;
    private FridgeFactory fridgeFactory;
    private FreezerFactory freezerFactory;

    @Autowired
    public AddStorageCommand(UserRepository userRepository, StorageRequest request, FridgeFactory fridgeFactory, FreezerFactory freezerFactory){
        this.request = request;
        this.userRepository = userRepository;
        this.fridgeFactory = fridgeFactory;
        this.freezerFactory = freezerFactory;
    }


    @Override
    public ResponseEntity<?> execute() {

        Optional<User> userOptional = userRepository.findById(request.getUserID());
        
        if (userOptional.isPresent()) {

            StorageFactory storageFactory;
            storageFactory = (request.getType() == StorageType.FREEZER) ? freezerFactory : fridgeFactory;

            Storage storage = storageFactory.createStorage();
            storage.setUser(userOptional);
            storage.setType(request.getType());
            storage.setCapacity(request.getCapacity());
            storage.setStorageName(request.getStorageName());
            storageFactory.save(storage);

            return ResponseEntity.ok("Storage added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
}
