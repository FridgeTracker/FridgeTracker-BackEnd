package com.example.FridgeTracker.ShoppingList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {
    public final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository){
        this.shoppingListRepository = shoppingListRepository;
    }
    
   
    public ResponseEntity<String> createNews_list(ShoppingList request){
        Optional<ShoppingList> existingListOpt = shoppingListRepository.findById(request.getS_listId());

        if (!existingListOpt.isPresent()){

            ShoppingList s_list = new ShoppingList();

            s_list.setS_listName(request.getS_listName());
            s_list.setItems(request.getItems());

            shoppingListRepository.save(s_list);
            return ResponseEntity.ok("New Shopping List is created.");

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to create shopping list");
        }
       
        
    }

    
    
    
}
