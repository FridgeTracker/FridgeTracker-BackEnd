package com.example.FridgeTracker.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }


    @PostMapping("/addItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addItemToFridge(@RequestBody ItemBody request){
        return itemService.addItemToFridge(request);

    }

    @PostMapping("/updateItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> updateItemInFridge(@RequestBody ItemBody request){
        return itemService.updateItemInFridge(request);
    }
    

    
    @PostMapping("/deleteItem")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> deleteItemInFridge(@RequestBody ItemBody request){
        return itemService.deleteItemInFridge(request);

    }
}
