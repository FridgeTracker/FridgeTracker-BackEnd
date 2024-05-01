package com.example.FridgeTracker.Storage.ShoppingList;

import java.util.List;

import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Storage.Storage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ShoppingList")
public class ShoppingList extends Storage{

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

}
