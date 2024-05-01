package com.example.FridgeTracker.Storage.Fridge;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;
import java.util.Optional;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Storage.Storage;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name="fridges")
public class Fridge extends Storage{

    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public void setUser(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            this.user = optionalUser.get();
        } else {
        }
    }


}

