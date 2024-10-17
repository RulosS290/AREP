package com.escuelaing.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing a customer.
 * This class maps to a database table and holds customer details.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    /**
     * Default constructor for JPA.
     */
    public Customer() {}

    /**
     * Constructor to initialize a Customer with first and last names.
     *
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Converts the customer object to a readable string format.
     * 
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    /**
     * Gets the customer's ID.
     * 
     * @return The ID of the customer.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the customer's first name.
     * 
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the customer's last name.
     * 
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }
}
