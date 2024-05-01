package com.example.FridgeTracker.Commands.StorageCommands;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.MealPlan.MealPlanService;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Storage.StorageType;
import com.example.FridgeTracker.Storage.StorageRequest;
import com.example.FridgeTracker.Storage.FactoryMethod.FreezerFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.FridgeFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.ShoppingListFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.StorageFactory;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

public class AddStorageCommand implements Command{

    private UserRepository userRepository;
    private StorageRequest request;
    private FridgeFactory fridgeFactory;
    private FreezerFactory freezerFactory;
    private ShoppingListFactory shoppingListFactory;
    
    private static final Logger logger = LoggerFactory.getLogger(AddStorageCommand.class);

    @Autowired
    public AddStorageCommand(UserRepository userRepository, StorageRequest request, FridgeFactory fridgeFactory, FreezerFactory freezerFactory, ShoppingListFactory shoppingListFactory){
        this.request = request;
        this.userRepository = userRepository;
        this.fridgeFactory = fridgeFactory;
        this.freezerFactory = freezerFactory;
        this.shoppingListFactory = shoppingListFactory;
    }


    @Override
    public ResponseEntity<?> execute() {

        Optional<User> userOptional = userRepository.findById(request.getUserID());
        
        if (userOptional.isPresent()) {

            StorageFactory storageFactory;
            if(request.getType() == StorageType.FREEZER){
                storageFactory = freezerFactory;
            }else if(request.getType() == StorageType.FRIDGE){
                storageFactory = fridgeFactory;
            }else {
                storageFactory = shoppingListFactory;
            }
            logger.info("",storageFactory);

            Storage storage = storageFactory.createStorage();
            storage.setUser(userOptional);
            logger.info("",storage.getUser());
            storage.setType(request.getType());
            if(request.getCapacity() == 0){
                storage.setCapacity(0);
            }else{
                storage.setCapacity(request.getCapacity());
            }
            storage.setStorageName(request.getStorageName());
            logger.info("",storage);
            storageFactory.save(storage);

            return ResponseEntity.ok("Storage added successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
}
