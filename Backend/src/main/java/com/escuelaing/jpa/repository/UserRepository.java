package com.escuelaing.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.escuelaing.jpa.model.User;


/**
 * Repository interface for managing User entities.
 * Extends the CrudRepository interface to provide CRUD operations for User entities.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return The User object with the matching username, or null if not found.
     */
    User findByUsername(String username);
}
