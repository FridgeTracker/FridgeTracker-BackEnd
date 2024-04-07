/*package com.example.FridgeTracker.Fridge; 

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import java.util.List;

import com.example.FridgeTracker.User.User;
import com.example.FridgeTracker.Item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Data
@Entity
@Table(name="fridges")
public class Fridge {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="fridgeName")
    private String fridgeName;

    @Column(name="capacity")
    private long capacity;

    @ManyToOne
    @JoinColumn(name="ownerEmail", referencedColumnName="email")
    @JsonIgnore
    private User owner;

    @OneToMany(mappedBy = "fridge", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;


    public void removeItem(Long itemId) {
        if (items != null) {    
            for (Item item : items) { 
                if (item.getFridgeID().equals(itemId)) {
                    items.remove(item);
                    item.setFridge(null);
                    break; 
                }
            }
        }
    }


}*/
