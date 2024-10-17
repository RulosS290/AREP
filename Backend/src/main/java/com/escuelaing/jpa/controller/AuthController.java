package com.escuelaing.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.escuelaing.jpa.model.User;
import com.escuelaing.jpa.service.AuthService;


/**
 * Controller class for handling user authentication-related requests.
 * Provides endpoints for user registration and login.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Registers a new user in the system.
     *
     * @param user The user object to be registered.
     * @return A ResponseEntity with a message indicating if the user
     *         was successfully registered or if the user already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String responseMessage = authService.registerUser(user);
        if (responseMessage.equals("Usuario ya registrado")) {
            return new ResponseEntity<>(responseMessage, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    /**
     * Authenticates a user based on provided credentials.
     *
     * @param loginRequest The login request containing the user's credentials.
     * @return A ResponseEntity indicating whether the login was successful or not.
     */
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        boolean isAuthenticated = authService.authenticateUser(loginRequest);
        if (isAuthenticated) {
            return new ResponseEntity<>("Login exitoso", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Credenciales inválidas", HttpStatus.UNAUTHORIZED);
        }
    }
}
