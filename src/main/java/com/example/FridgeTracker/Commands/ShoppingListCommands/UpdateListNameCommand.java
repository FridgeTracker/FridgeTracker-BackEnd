package com.example.FridgeTracker.Commands.ShoppingListCommands;

import java.util.Optional;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.ShoppingList.ShoppingListRepository;

public class UpdateListNameCommand implements Command{
    
    private ShoppingList request;
    private ShoppingListRepository shoppingListRepository;

    public UpdateListNameCommand(ShoppingList request, ShoppingListRepository shoppingListRepository) {
        this.request = request;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<ShoppingList> optShoppingList = shoppingListRepository.findById(request.getS_listId());
    
        if (optShoppingList.isPresent()) {
            ShoppingList updateShoppingList = optShoppingList.get();
            updateShoppingList.setS_listName(request.getS_listName());
            shoppingListRepository.save(updateShoppingList);
            
            return ResponseEntity.ok("Shopping list name updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
