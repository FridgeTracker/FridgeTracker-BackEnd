package com.example.FridgeTracker.Fridge;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.FridgeTracker.User.User;
import java.util.List;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {

    List<Fridge> findByOwner(User owner);
    
} 
