package com.example.FridgeTracker.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Commands.CommandInvoker;
import com.example.FridgeTracker.Commands.ItemCommands.AddItemCommand;
import com.example.FridgeTracker.Commands.ItemCommands.DeleteItemCommand;
import com.example.FridgeTracker.Commands.ItemCommands.UpdateItemCommand;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemRepository itemRepository;
    private final FridgeRepository fridgeRepository;
    private final FreezerRepository freezerRepository;
    private final FoodDataRepository foodDataRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository,FridgeRepository fridgeRepository,FreezerRepository freezerRepository,FoodDataRepository foodDataRepository, ShoppingListRepository shoppingListRepository){
    
        this.itemRepository = itemRepository;
        this.fridgeRepository = fridgeRepository;
        this.freezerRepository = freezerRepository;
        this.foodDataRepository = foodDataRepository;
        this.shoppingListRepository = shoppingListRepository;
    }


    @PostMapping("/addItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> addItemToFridge(@RequestBody ItemBody request){
        Command createCommand = new AddItemCommand(request,itemRepository,fridgeRepository,freezerRepository,foodDataRepository,shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    @PostMapping("/updateItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> updateItemInFridge(@RequestBody ItemBody request){
        Command createCommand = new UpdateItemCommand(request,fridgeRepository,freezerRepository,shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }

    
    @PostMapping("/deleteItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> deleteItemInFridge(@RequestBody ItemBody request){
        Command createCommand = new DeleteItemCommand(request,fridgeRepository,freezerRepository,shoppingListRepository);
        CommandInvoker invoker = new CommandInvoker(createCommand);
        return invoker.executeCommand();
    }
}
