package com.example.FridgeTracker.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, UUID> {
    
    User findByEmail(String email);
    boolean existsByEmail(String email);

    Optional<User> findById(UUID id);

    List<User> findAll();

    //Add get all Users method
}

