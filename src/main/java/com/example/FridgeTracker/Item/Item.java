package com.example.FridgeTracker.Item;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Optional;

import com.example.FridgeTracker.Storage.Fridge; 

//Item class to store food items
@Data
@Entity
@Table(name="items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID")
    private Long fridgeID;
    
    @Column(name="foodName")
    private String foodName;

    @Column(name="quantity")
    private int quantity;

    @Column(name="calories")
    private Long calories;

    //Used to declare enums ( MEAT, DAIRY, VEGETABLE, FRUIT )
    @Column(name="type")
    private String type;

    @ManyToOne
    @JoinColumn(name="fridgeIdentifier", referencedColumnName="id")
    @JsonIgnore
    private Fridge fridge;

    // Overloaded method to accept Optional<Fridge>
    public void setFridge(Optional<Fridge> optionalFridge) {
        if (optionalFridge.isPresent()) {
            this.fridge = optionalFridge.get();
        } else {
        }
    }

}
    