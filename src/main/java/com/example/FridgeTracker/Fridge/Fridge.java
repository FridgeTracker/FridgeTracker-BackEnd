package com.example.FridgeTracker.Fridge; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

import com.example.FridgeTracker.User.User;


@Data
@Entity
@Table(name="fridges")
public class Fridge {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fridgeName")
    private String fridgeName;

    @Column(name="capacity")
    private long capacity;

    @ManyToOne
    @JoinColumn(name="ownerEmail", referencedColumnName="email")
    private User owner;


}
