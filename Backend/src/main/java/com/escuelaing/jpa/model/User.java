package com.escuelaing.jpa.model;

import jakarta.persistence.*;

/**
 * Entity class representing a user in the system.
 * This class maps to a database table and holds details about the user, 
 * including their username and encrypted password.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    /**
     * Default constructor for JPA.
     */
    public User() {}

    /**
     * Constructor to initialize a User with username and password.
     * 
     * @param username The username of the user.
     * @param password The password of the user (should be encrypted).
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password; 
    }

    /**
     * Gets the user's ID.
     * 
     * @return The user ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return The user's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username The new username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * @param password The new password of the user (should be encrypted).
     */
    public void setPassword(String password) {
        this.password = password; 
    }
}
