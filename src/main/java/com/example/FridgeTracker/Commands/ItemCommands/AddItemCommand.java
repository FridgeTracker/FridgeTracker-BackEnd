package com.example.FridgeTracker.Commands.ItemCommands;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.FridgeTracker.Commands.Command;
import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.DataSets.FoodDataRepository;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Item.ItemBody;
import com.example.FridgeTracker.Item.ItemRepository;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Freezer.FreezerRepository;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.Fridge.FridgeRepository;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingListRepository;

public class AddItemCommand implements Command{

    private ItemBody request;
    private final ItemRepository itemRepository;
    private final FridgeRepository fridgeRepository;
    private final FreezerRepository freezerRepository;
    private final FoodDataRepository foodDataRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public AddItemCommand(ItemBody request, ItemRepository itemRepository,FridgeRepository fridgeRepository,FreezerRepository freezerRepository,
                                FoodDataRepository foodDataRepository, ShoppingListRepository shoppingListRepository){
    
        this.itemRepository = itemRepository;
        this.fridgeRepository = fridgeRepository;
        this.freezerRepository = freezerRepository;
        this.foodDataRepository = foodDataRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.request = request;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<Fridge> fridge = fridgeRepository.findById(request.getId());
        Optional<Freezer> freezer = freezerRepository.findById(request.getId());
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(request.getId());

        if (fridge.isPresent() || freezer.isPresent() || shoppingList.isPresent()){

            Item item = new Item();
            Optional<FoodData> food_item = Optional.empty();
            
            if(request.getFoodID() != null){
                food_item = foodDataRepository.findById(request.getFoodID());
                item.setFoodID(food_item.get());
            }

            item.setFoodName(request.getFoodName());
            item.setQuantity(request.getQuantity());

            if(request.getExpiryDate() != null){
                item.setExpiryDate(request.getExpiryDate());
            }

            if(freezer.isPresent()){
                item.setFreezer(freezer);
            }
            else if(fridge.isPresent()){
                item.setFridge(fridge);
            } else {
                item.setShoppingList(shoppingList.get());
            }

            itemRepository.save(item);

            return ResponseEntity.ok("Item added successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fail to add Item");
        }
    }
    
}
