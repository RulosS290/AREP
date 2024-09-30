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

/**
 * Servicio para gestionar la lógica de negocio relacionada con los clientes.
 * Proporciona métodos para operaciones CRUD y paginación.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return Lista de todos los clientes.
     */
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    /**
     * Crea un nuevo cliente.
     *
     * @param customer Objeto Customer que se va a guardar.
     * @return Cliente guardado.
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Actualiza un cliente existente.
     *
     * @param id ID del cliente a actualizar.
     * @param newCustomerData Datos nuevos del cliente.
     * @return Cliente actualizado.
     * @throws RelationNotFoundException si no se encuentra el cliente con el ID proporcionado.
     */
    public Customer updateCustomer(Long id, Customer newCustomerData) throws RelationNotFoundException {
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

    /**
     * Elimina un cliente por su ID.
     *
     * @param id ID del cliente a eliminar.
     * @throws ResourceAccessException si no se encuentra el cliente con el ID proporcionado.
     */
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }

    /**
     * Obtiene una lista paginada de clientes.
     *
     * @param page Número de página a recuperar.
     * @param size Tamaño de la página.
     * @return Página de clientes.
     */
    public Page<Customer> getCustomers(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }
}

