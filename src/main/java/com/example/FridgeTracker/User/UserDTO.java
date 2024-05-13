package com.example.FridgeTracker.User;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class UserDTO {
    private String familyName;
    private String email;
    private String password;
    private int rank;
    private String imageData;
    private String timezone;
    private boolean storageEmpty;
    private boolean storageFull;
    private boolean expiryDate;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    public UUID id;

    public UUID getId() {
        return id;
    }
    
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isStorageEmpty() {
        return storageEmpty;
    }

    public void setStorageEmpty(boolean storageEmpty) {
        this.storageEmpty = storageEmpty;
    }

    public boolean isStorageFull() {
        return storageFull;
    }

    public void setStorageFull(boolean storageFull) {
        this.storageFull = storageFull;
    }

    public boolean isExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(boolean expiryDate) {
        this.expiryDate = expiryDate;
    }
}
