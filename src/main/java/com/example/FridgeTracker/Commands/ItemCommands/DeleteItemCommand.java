package com.example.FridgeTracker.Commands.ItemCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Item.ItemBody;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.ShoppingList.ShoppingListRepository;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;

public class DeleteItemCommand implements Command{

    private ItemBody request;
    private final FridgeRepository fridgeRepository;
    private final FreezerRepository freezerRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public DeleteItemCommand(ItemBody request, FridgeRepository fridgeRepository,FreezerRepository freezerRepository,ShoppingListRepository shoppingListRepository){
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
            Item itemToRemove = itemOptional.get();

            if(fridgeOptional.isPresent()){
                fridge.getItems().remove(itemToRemove);
                fridgeRepository.save(fridge); 
            }else if(freezerOptional.isPresent()){
                freezer.getItems().remove(itemToRemove);
                freezerRepository.save(freezer); 
            }else if(listOptional.isPresent()){
                list.getItems().remove(itemToRemove);
                shoppingListRepository.save(list); 
            }
        
            return ResponseEntity.ok("Item deleted successfully.");
        } else {
            return ResponseEntity.badRequest().body("no item id" + request.getId());
        }    
    

    }
}
    

