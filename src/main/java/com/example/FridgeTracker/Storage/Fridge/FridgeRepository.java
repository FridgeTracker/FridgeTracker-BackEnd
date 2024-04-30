package com.example.FridgeTracker.Storage.Fridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.FridgeTracker.User.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FridgeRepository extends JpaRepository<Fridge, UUID> {

    List<Fridge> findByUser(User user);
    Optional<Fridge> findById(UUID id);

} 
