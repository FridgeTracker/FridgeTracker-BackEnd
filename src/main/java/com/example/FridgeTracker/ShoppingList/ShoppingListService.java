package com.example.FridgeTracker.ShoppingList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

@Service
public class ShoppingListService {
    public final ShoppingListRepository shoppingListRepository;
    public final UserRepository userRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository){
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
    }
    
   
    public ResponseEntity<String> createNews_list(ShoppingListRequest request){

        Optional<User> optionalUser = userRepository.findById(request.getUserID());

        if (optionalUser.isPresent()){

            ShoppingList s_list = new ShoppingList();

            s_list.setUser(optionalUser.get());
            s_list.setS_listName(request.getShoppingList().getS_listName());
            s_list.setItems(request.getShoppingList().getItems());

            shoppingListRepository.save(s_list);

            return ResponseEntity.ok("New Shopping List is created.");

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to create shopping list");
        }
       
        
    }

    
    
    
}
