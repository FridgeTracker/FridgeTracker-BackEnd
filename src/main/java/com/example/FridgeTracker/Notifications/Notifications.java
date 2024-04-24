package com.example.FridgeTracker.Notifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.example.FridgeTracker.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="notifcation_data")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="Sender")
    String sender;

    @Column(name="message")
    String message;

    @Column(name="alert_type")
    String alert_type;

    @Column(name="dateTime")
    LocalDateTime dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="id")
    @JsonIgnore
    private User user;

    // Overloaded method to accept Optional<Fridge>
    public void setUser(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            this.user = optionalUser.get();
        } else {
        }
    }
    
}
