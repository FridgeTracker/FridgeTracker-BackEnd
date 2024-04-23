package com.example.FridgeTracker.ShoppingList;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.example.FridgeTracker.Item.Item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Entity
@Table(name="ShoppingList")
public class ShoppingList {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "s_listId", updatable = false, nullable = false)
    private UUID s_listId;

    @Column(name="s_listName")
    private String s_listName;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

}
