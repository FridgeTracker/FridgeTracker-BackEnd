package com.example.FridgeTracker.Item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

   // List<Item> findByFridge(Fridge fridge);

}
