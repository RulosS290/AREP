package com.escuelaing.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing a property.
 * This class maps to a database table and holds details about a property.
 */
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
    private Double price;
    private Double size;
    private String description;

    /**
     * Default constructor for JPA.
     */
    public Property() {}

    /**
     * Constructor to initialize a Property with its details.
     *
     * @param address The address of the property.
     * @param price The price of the property.
     * @param size The size of the property (in square meters, etc.).
     * @param description A brief description of the property.
     */
    public Property(String address, Double price, Double size, String description) {
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    /**
     * Gets the ID of the property.
     * 
     * @return The property ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the address of the property.
     * 
     * @return The property address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the property.
     * 
     * @param address The new address of the property.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the price of the property.
     * 
     * @return The property price.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Sets the price of the property.
     * 
     * @param price The new price of the property.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Gets the size of the property.
     * 
     * @return The property size.
     */
    public Double getSize() {
        return size;
    }

    /**
     * Sets the size of the property.
     * 
     * @param size The new size of the property.
     */
    public void setSize(Double size) {
        this.size = size;
    }

    /**
     * Gets the description of the property.
     * 
     * @return The property description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the property.
     * 
     * @param description The new description of the property.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Converts the property object to a readable string format.
     * 
     * @return A string representation of the property.
     */
    @Override
    public String toString() {
        return String.format("Property[id=%d, address='%s', price=%.2f, size=%.2f, description='%s']", id, address, price, size, description);
    }
}
