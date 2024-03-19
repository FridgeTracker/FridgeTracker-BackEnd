package com.example.FridgeTracker.Member;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.example.FridgeTracker.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//Needs getters and setters using lombok for now
@Data
@Entity
@Table(name="members")
public class Member {

    //Auto generated unique UUID
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="allergies")
    private String allergies;

    @Column(name="height")
    private double height;

    @Column(name="weight")
    private double weight;

    //Connect Members to a family account
    @ManyToOne
    @JoinColumn(name="ownerEmail", referencedColumnName="email")
    @JsonIgnore
    private User family;

    
}
