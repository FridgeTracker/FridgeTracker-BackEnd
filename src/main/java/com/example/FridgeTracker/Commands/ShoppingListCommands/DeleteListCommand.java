/*package com.example.FridgeTracker.Commands.ShoppingListCommands;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;

public class DeleteListCommand implements Command {

    private ShoppingList request;
    private ShoppingListRepository shoppingListRepository;

    public DeleteListCommand(ShoppingList request, ShoppingListRepository shoppingListRepository) {
        this.request = request;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(request.getS_listId());

        if(shoppingList.isPresent()){
            shoppingListRepository.delete(shoppingList.get());
            return ResponseEntity.ok("Shopping list deleted successfully.");
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    
}*/
