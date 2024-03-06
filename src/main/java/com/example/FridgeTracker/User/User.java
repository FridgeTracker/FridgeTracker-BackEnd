package com.example.FridgeTracker.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "rank")
    private int rank;

    //Constructor
    User(String email, String password, int rank){
        this.email = email;
        this.password = password;
        this.rank = rank;
    }

    //Getters
    public long getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getRank(){
        return rank;
    }

    //Setters
    public void setId(long id){
        this.id = id;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }

}
