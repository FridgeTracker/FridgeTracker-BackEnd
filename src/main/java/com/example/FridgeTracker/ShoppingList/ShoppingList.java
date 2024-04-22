package com.example.FridgeTracker.ShoppingList;

import java.util.Optional;

import com.example.FridgeTracker.DataSets.FoodData;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name="ShoppingList")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private Long itemID;
    
    @Column(name="foodName")
    private String foodName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="category")
    String FoodCategory;

    @ManyToOne
    @JoinColumn(name="food_id", referencedColumnName="id")
    private FoodData foodID;

   /*  public void setShoppingList(Optional<ShoppingList> optionalShoppingList) {
        if (optionalShoppingList.isPresent()) {
            this.shoppingList = optionalShoppingList.get();
        } else {
        }
    }*/
}
