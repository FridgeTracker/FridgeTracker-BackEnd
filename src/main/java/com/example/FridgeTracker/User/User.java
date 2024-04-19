package com.example.FridgeTracker.User;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import com.example.FridgeTracker.Member.Member;
import com.example.FridgeTracker.Storage.Storage;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Fridge.Fridge;

import java.util.UUID;

@Data
@Entity
@Table(name="users")
public class User {

    // Auto generate UUID *unique*
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    //also a username
    @Column(name = "familyName", length = 255)
    private String familyName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "rank")
    private int rank;

    @Column(name="profile_image")
    private String imageData;

    //Connect added fridges to account
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Fridge> fridges;

    //Connect added fridges to account
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Freezer> freezers;

    //Connect added Members to account
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Member> members;


}
