package com.example.FridgeTracker.User;

import java.util.List;
import java.util.UUID;

import com.example.FridgeTracker.Member.Member;
import com.example.FridgeTracker.Notifications.Notifications;
import com.example.FridgeTracker.Storage.Freezer.Freezer;
import com.example.FridgeTracker.Storage.Fridge.Fridge;
import com.example.FridgeTracker.Storage.ShoppingList.ShoppingList;

public class UserBuilder {
    private User user;

    public UserBuilder() {
        this.user = new User();
    }

    public UserBuilder setId(UUID id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder setFamilyName(String familyName) {
        this.user.setFamilyName(familyName);
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.user.setEmail(email);
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder setRank(int rank) {
        this.user.setRank(rank);
        return this;
    }

    public UserBuilder setImageData(String imageData) {
        this.user.setImageData(imageData);
        return this;
    }

    public UserBuilder withTimezone(String timezone) {
        user.setTimezone(timezone);
        return this;
    }

    public UserBuilder withStorageEmpty(boolean storageEmpty) {
        user.setStorageEmpty(storageEmpty);
        return this;
    }

    public UserBuilder withStorageFull(boolean storageFull) {
        user.setStorageFull(storageFull);
        return this;
    }

    public UserBuilder withExpiryDate(boolean expiryDate) {
        user.setExpiryDate(expiryDate);
        return this;
    }

    public UserBuilder withFridges(List<Fridge> fridges) {
        user.setFridges(fridges);
        return this;
    }

    public UserBuilder withFreezers(List<Freezer> freezers) {
        user.setFreezers(freezers);
        return this;
    }

    public UserBuilder withMembers(List<Member> members) {
        user.setMembers(members);
        return this;
    }

    public UserBuilder withNotifications(List<Notifications> notifications) {
        user.setNotification(notifications);
        return this;
    }

    public UserBuilder withShoppingLists(List<ShoppingList> shoppingLists) {
        user.setShoppingLists(shoppingLists);
        return this;
    }

    public User build() {
        return this.user;
    }
}
