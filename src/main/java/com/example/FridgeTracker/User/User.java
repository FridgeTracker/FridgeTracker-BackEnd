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

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "rank")
    private int rank;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Fridge> fridges;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<Member> members;


}
