package com.example.FridgeTracker.Storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Commands.CommandInvoker;
import com.example.FridgeTracker.Commands.StorageCommands.AddStorageCommand;
import com.example.FridgeTracker.Commands.StorageCommands.DeleteStorageCommand;
import com.example.FridgeTracker.Storage.FactoryMethod.FreezerFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.FridgeFactory;
import com.example.FridgeTracker.Storage.FactoryMethod.ShoppingListFactory;
import com.example.FridgeTracker.User.UserRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StorageController {

    private final UserRepository userRepository;
    private final FridgeFactory fridgeFactory;
    private final FreezerFactory freezerFactory;
    private final ShoppingListFactory shoppingListFactory;

    @Autowired
    public StorageController(UserRepository userRepository, FridgeFactory fridgeFactory, FreezerFactory freezerFactory, ShoppingListFactory shoppingListFactory) {
        this.userRepository = userRepository;
        this.fridgeFactory = fridgeFactory;
        this.freezerFactory = freezerFactory;
        this.shoppingListFactory = shoppingListFactory;
    }

    @PostMapping("/addStorage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addStorageToUser(@RequestBody StorageRequest request){
        Command createCommand = new AddStorageCommand(userRepository, request, fridgeFactory, freezerFactory, shoppingListFactory);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    @PostMapping("/deleteStorage")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteStorage(@RequestBody StorageRequest request) {
        Command createCommand = new DeleteStorageCommand(request, fridgeFactory, freezerFactory, shoppingListFactory);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }


}
