package com.example.FridgeTracker.DataSets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="foodData")
public class FoodData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="category")
    String FoodCategory;

    @Column(name="food_item")
    String FoodItem;

    @Column(name="grams")
    String per100grams;

    @Column(name="calories")
    String Cals_per100grams;

    @Column(name="kilojules")
    String KJ_per100grams;


}
