package com.example.FridgeTracker.Item;

import com.example.FridgeTracker.DataSets.FoodData;
import com.example.FridgeTracker.ShoppingList.ShoppingList;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.util.Optional; 

//Item class to store food items
@Data
@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private Long itemID;
    
    @Column(name="foodName")
    private String foodName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="expiry_date")
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name="food_id", referencedColumnName="id")
    private FoodData foodID;

    @ManyToOne
    @JoinColumn(name="fridge_id", referencedColumnName="id")
    @JsonIgnore
    private Fridge fridge;

    @ManyToOne
    @JoinColumn(name="freezer_id", referencedColumnName="id")
    @JsonIgnore
    private Freezer freezer;

    @ManyToOne
    @JoinColumn(name="s_listId", referencedColumnName="id")
    @JsonIgnore
    private ShoppingList shoppingList;

    // Overloaded method to accept Optional<Fridge>
    public void setFridge(Optional<Fridge> optionalFridge) {
        if (optionalFridge.isPresent()) {
            this.fridge = optionalFridge.get();
        } else {
        }
    }

    public void setFreezer(Optional<Freezer> optionalFreezer) {
        if (optionalFreezer.isPresent()) {
            this.freezer = optionalFreezer.get();
        } else {
        }
    }

}
    