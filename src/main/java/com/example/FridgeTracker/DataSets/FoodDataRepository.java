package com.example.FridgeTracker.DataSets;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDataRepository  extends JpaRepository<FoodData, Long> {
    
}
