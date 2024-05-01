/*package com.example.FridgeTracker.Commands.ShoppingListCommands;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.ShoppingList.ShoppingListRequest;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;
import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

public class CreateShoppingListCommand implements Command{

    private ShoppingListRequest request;
    private UserRepository userRepository;
    private ShoppingListRepository shoppingListRepository;

    public CreateShoppingListCommand(ShoppingListRequest request, UserRepository userRepository, ShoppingListRepository shoppingListRepository) {
        this.request = request;
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<User> optionalUser = userRepository.findById(request.getUserID());

        if (optionalUser.isPresent()) {
            ShoppingList s_list = new ShoppingList();
            s_list.setUser(optionalUser.get());
            s_list.setS_listName(request.getS_listName());
            shoppingListRepository.save(s_list);
            return ResponseEntity.ok("New Shopping List is created.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to create shopping list");
        }
    }

}
*/