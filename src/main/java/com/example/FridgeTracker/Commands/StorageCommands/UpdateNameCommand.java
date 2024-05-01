package com.example.FridgeTracker.Commands.StorageCommands;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;

public class UpdateNameCommand implements Command{
    
    private ShoppingList request;
    private ShoppingListRepository shoppingListRepository;

    public UpdateNameCommand(ShoppingList request, ShoppingListRepository shoppingListRepository) {
        this.request = request;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(request.getId());
    
        if (optShoppingList.isPresent()) {
            ShoppingList updateShoppingList = optShoppingList.get();
            updateShoppingList.setStorageName(request.getStorageName());
            shoppingListRepository.save(updateShoppingList);
            
            return ResponseEntity.ok("Shopping list name updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
