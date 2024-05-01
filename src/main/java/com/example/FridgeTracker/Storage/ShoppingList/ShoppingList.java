package com.example.FridgeTracker.Storage.ShoppingList;

import java.util.List;
import java.util.Optional;

import com.example.FridgeTracker.Item.Item;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ShoppingList")
public class ShoppingList extends Storage{

    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    public void setUser(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            this.user = optionalUser.get();
        } else {
        }
    }

}
