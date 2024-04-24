package com.example.FridgeTracker.ShoppingList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.User.UserRepository;

import jakarta.transaction.Transactional;

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
            s_list.setS_listName(request.getS_listName());

            shoppingListRepository.save(s_list);

            return ResponseEntity.ok("New Shopping List is created.");

        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to create shopping list");
        }
       
        
    }


    public ResponseEntity<String> saveList(ShoppingListRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getUserID());

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
    
            Optional<ShoppingList> listOptional = user.getShoppingLists().stream()
                                    .filter(list -> list.getS_listId().equals(request.getS_listId()))
                                    .findFirst();
    
            if (listOptional.isPresent()) {

                ShoppingList newList = listOptional.get();
                
                newList.getItems().clear();
                newList.setItems(request.getItems());
    
                userRepository.save(user);
    
                return ResponseEntity.ok("Shopping List updated successfully");
            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("List could not be found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to find user account");
        }

    }


    public ResponseEntity<String> deleteList(ShoppingList request){

        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(request.getS_listId());

        if(shoppingList.isPresent()){
            shoppingListRepository.delete(shoppingList.get());
            return ResponseEntity.ok("Shopping list deleted successfully.");
        } else{
            return ResponseEntity.notFound().build();
        }


    }

    public ResponseEntity<String> changeListName(ShoppingList request) {
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
