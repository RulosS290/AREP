package com.escuelaing.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escuelaing.jpa.model.Customer;

/**
 * Repositorio para gestionar operaciones de persistencia de la entidad Customer.
 * Extiende JpaRepository para proporcionar operaciones CRUD y acceso a datos.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * Encuentra una lista de clientes por su apellido.
     *
     * @param lastName Apellido del cliente a buscar.
     * @return Lista de clientes que coinciden con el apellido proporcionado.
     */
    List<Customer> findByLastName(String lastName);

    /**
     * Encuentra un cliente por su ID.
     *
     * @param id ID del cliente a buscar.
     * @return Cliente con el ID proporcionado, o null si no se encuentra.
     */
    Customer findById(long id);

    /**
     * Encuentra todos los clientes paginados.
     *
     * @param pageable Objeto Pageable que contiene información de paginación.
     * @return Página de clientes.
     */
    Page<Customer> findAll(Pageable pageable);
}

