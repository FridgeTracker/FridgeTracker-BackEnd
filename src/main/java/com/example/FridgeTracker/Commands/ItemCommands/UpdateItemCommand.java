package com.example.FridgeTracker.Commands.ItemCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Commands.CommandInvoker;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Item.ItemBody;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;

public class UpdateItemCommand implements Command{

    private ItemBody request;
    private final FridgeRepository fridgeRepository;
    private final FreezerRepository freezerRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public UpdateItemCommand(ItemBody request,FridgeRepository fridgeRepository,FreezerRepository freezerRepository,ShoppingListRepository shoppingListRepository){
    
        this.fridgeRepository = fridgeRepository;
        this.freezerRepository = freezerRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.request = request;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<Fridge> fridgeOptional = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezerOptional = freezerRepository.findById(request.getId());
        Optional<ShoppingList> listOptional = shoppingListRepository.findById(request.getId());

        Fridge fridge = null;
        Freezer freezer = null;
        ShoppingList list = null;
        Optional<Item> itemOptional;

        if (fridgeOptional.isPresent()){

                fridge = fridgeOptional.get();
                itemOptional = fridge.getItems().stream()
                                        .filter(item -> item.getItemID().equals(request.getItemID()))
                                        .findFirst();
        } 
        else if(freezerOptional.isPresent()){

                freezer = freezerOptional.get();
                itemOptional = freezer.getItems().stream()
                                        .filter(item -> item.getItemID().equals(request.getItemID()))
                                        .findFirst();
        }
        else if(listOptional.isPresent()){

            list = listOptional.get();
            itemOptional = list.getItems().stream()
                                    .filter(item -> item.getItemID().equals(request.getItemID()))
                                    .findFirst();
    }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to open");
        }

        if (itemOptional.isPresent()) {
            
            boolean shouldDelete = false;

            while(true){
                Item item = itemOptional.get();

                item.setQuantity(request.getQuantity());

                if(item.getQuantity() == 0){
                    shouldDelete = true;
                }

                if(request.getFoodName() != null){
                    item.setFoodName(request.getFoodName());
                }
                if(request.getExpiryDate() != null){
                    item.setExpiryDate(request.getExpiryDate());
                }
                break;
            }

            if(shouldDelete){
                Command createCommand = new DeleteItemCommand(request,fridgeRepository,freezerRepository,shoppingListRepository);
                CommandInvoker invoker = new CommandInvoker(createCommand);
                return invoker.executeCommand();
            }
            
            // Save the updated fridge back to the database
            if(fridgeOptional.isPresent()){
                fridgeRepository.save(fridge);
            }else if(freezerOptional.isPresent()){
                freezerRepository.save(freezer);
            }else if(listOptional.isPresent()){
                shoppingListRepository.save(list);
            }

            return ResponseEntity.ok("Item updated successfully");
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found in the fridge");
        }
    }
    
}
