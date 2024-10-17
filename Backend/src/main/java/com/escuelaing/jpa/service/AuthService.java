package com.escuelaing.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.escuelaing.jpa.model.User;
import com.escuelaing.jpa.repository.UserRepository;




/**
 * Service class for handling user authentication and registration.
 * This service encrypts passwords and verifies login credentials.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user by encrypting their password and saving them to the database.
     * 
     * @param user The user to register.
     * @return A message indicating success or failure.
     */
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "User already registered";
        }
        // Encrypt the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User successfully registered";
    }

    /**
     * Authenticates a user by comparing the provided password with the stored hash.
     * 
     * @param loginRequest The login request containing the username and password.
     * @return A boolean indicating if the authentication was successful.
     */
    public boolean authenticateUser(User loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
        
        if (user == null) {
            System.out.println("User not found");
            return false;
        }
    
        System.out.println("Entered plaintext password: " + loginRequest.getPassword());
        System.out.println("Stored hashed password: " + user.getPassword());
    
        // Compare the passwords
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            System.out.println("Correct password!");
            return true;
        } else {
            System.out.println("Incorrect password");
            return false;
        }
    }
    
}
