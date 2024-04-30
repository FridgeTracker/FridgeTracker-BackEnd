package com.example.FridgeTracker.Storage;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Storage {

    public enum StorageType {
        FREEZER,
        FRIDGE
    }
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name="storageName")
    private String storageName;

    @Column(name="capacity")
    private long capacity;

    @Column(name="type")
    private StorageType type;

}
