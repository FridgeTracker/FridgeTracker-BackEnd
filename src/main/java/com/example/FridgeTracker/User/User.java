package com.example.FridgeTracker.User;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import com.example.FridgeTracker.Fridge.Fridge; 
import com.example.FridgeTracker.Member.Member;
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

    //Connect added fridges to account
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Fridge> fridges;

    //Connect added Members to account
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Member> members;


}
