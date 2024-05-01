package com.example.FridgeTracker.ShoppingList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Commands.CommandInvoker;
import com.example.FridgeTracker.Commands.ShoppingListCommands.CreateShoppingListCommand;
import com.example.FridgeTracker.Commands.ShoppingListCommands.DeleteListCommand;
import com.example.FridgeTracker.Commands.ShoppingListCommands.UpdateListNameCommand;
import com.example.FridgeTracker.User.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ShoppingListController {

    public final ShoppingListRepository shoppingListRepository;
    public final UserRepository userRepository;

    @Autowired
    public ShoppingListController(UserRepository userRepository, ShoppingListRepository shoppingListRepository){
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    @PostMapping("/create")
    @CrossOrigin(origins="*")
    public ResponseEntity<?> createNews_list(@RequestBody ShoppingListRequest request){
        Command createCommand = new CreateShoppingListCommand(request, userRepository, shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    @PostMapping("/deleteList")
    @CrossOrigin(origins="*")
    public ResponseEntity<?> deleteList(@RequestBody ShoppingList request){
        Command createCommand = new DeleteListCommand(request, shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    @PostMapping("/changeListName")
    @CrossOrigin(origins ="*")
    public ResponseEntity<?> changeListName(@RequestBody ShoppingList request){
        Command createCommand = new UpdateListNameCommand(request, shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }
}
