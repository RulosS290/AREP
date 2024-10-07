package com.escuelaing.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Clase que representa un cliente en el sistema.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID único del cliente.

    private String firstName; // Nombre del cliente.
    private String lastName; // Apellido del cliente.
    private String address; // Dirección del cliente.
    private int price; // Precio asociado al cliente.
    private int size; // Tamaño asociado al cliente.
    private String description; // Descripción del cliente.

    // Constructor protegido para uso interno.
    protected Customer() {}

    /**
     * Constructor que inicializa un nuevo cliente.
     *
     * @param firstName  Nombre del cliente.
     * @param lastName   Apellido del cliente.
     * @param address    Dirección del cliente.
     * @param price      Precio asociado al cliente.
     * @param size       Tamaño asociado al cliente.
     * @param description Descripción del cliente.
     */
    public Customer(String firstName, String lastName, String address, int price, int size, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.price = price;
        this.size = size;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s', address='%s', price=%d, size=%d, description='%s']",
            id, firstName, lastName, address, price, size, description);
    }

    // Métodos getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


