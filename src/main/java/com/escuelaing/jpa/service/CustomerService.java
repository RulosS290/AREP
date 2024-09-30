package com.escuelaing.jpa.service;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.escuelaing.jpa.model.Customer;
import com.escuelaing.jpa.repository.CustomerRepository;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Obtener todos los Customer
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    // Crear un nuevo Customer
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Actualizar un cliente existente
    public Customer updateCustomer(Long id, Customer newCustomerData) throws RelationNotFoundException{
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomerData.getFirstName());
                    customer.setLastName(newCustomerData.getLastName());
                    customer.setAddress(newCustomerData.getAddress());
                    customer.setPrice(newCustomerData.getPrice());
                    customer.setSize(newCustomerData.getSize());
                    customer.setDescription(newCustomerData.getDescription());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RelationNotFoundException("Customer not found with id " + id));
    }

    // Eliminar un cliente por id
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }

    public Page<Customer> getCustomers(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }

}
