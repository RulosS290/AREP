package com.escuelaing.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escuelaing.jpa.model.Customer;
import com.escuelaing.jpa.repository.CustomerRepository;

@Service
public class CustormerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustormerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Obtener todos los usuarios
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    // Crear un nuevo usuario
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    // Otros métodos de negocio como actualización, eliminación, etc.
}
