package com.example.FridgeTracker.Storage.ShoppingList;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShoppingListRepository extends JpaRepository<ShoppingList, UUID> {
    Optional<ShoppingList>findById(UUID s_listId);
}
