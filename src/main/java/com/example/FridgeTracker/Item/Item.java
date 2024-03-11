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

import com.example.FridgeTracker.Fridge.Fridge; 


@Data
@Entity
@Table(name="items")
public class Item {

    @Id
    @Column(name = "fridgeID")
    private Long fridgeID;
    
    @Column(name="foodName")
    private String foodName;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="fridgeID", referencedColumnName="id", updatable=false, insertable=false, nullable=false)
    @JsonIgnore
    private Fridge fridge;


}
    