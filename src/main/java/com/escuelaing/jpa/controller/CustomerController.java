package com.escuelaing.jpa.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.escuelaing.jpa.model.Customer;
import com.escuelaing.jpa.service.CustomerService;

/**
 * Controlador para gestionar las operaciones relacionadas con los clientes.
 */
@Controller
@RequestMapping("/Customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Muestra la lista de clientes paginada.
     *
     * @param model Modelo para la vista.
     * @param page  Número de página para la paginación.
     * @return Nombre de la vista a mostrar.
     */
    @GetMapping
    public String listCustomers(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Customer> customers = customerService.getCustomers(page, 10);
        model.addAttribute("customers", customers.getContent());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", page);
        return "index";
    }

    /**
     * Devuelve todos los clientes en formato JSON.
     *
     * @return Lista de todos los clientes.
     */
    @GetMapping("/all")
    @ResponseBody
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    /**
     * Muestra la vista para crear un nuevo cliente.
     *
     * @return Nombre de la vista de creación.
     */
    @GetMapping("/createCustomer")
    public String create() {
        return "create";
    }

    /**
     * Crea un nuevo cliente y redirige a la lista de clientes.
     *
     * @param customer            Objeto cliente a crear.
     * @param redirectAttributes  Atributos para redireccionar.
     * @return Redirección a la lista de clientes.
     */
    @PostMapping("/createCustomer/create")
    public String createCustomer(@ModelAttribute Customer customer, RedirectAttributes redirectAttributes) {
        try {
            customerService.saveCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente creado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al crear el cliente.");
        }
        return "redirect:/Customers";
    }

    /**
     * Actualiza un cliente existente y redirige a la lista de clientes.
     *
     * @param id                  ID del cliente a actualizar.
     * @param newCustomerData     Nuevos datos del cliente.
     * @param redirectAttributes   Atributos para redireccionar.
     * @return Redirección a la lista de clientes.
     */
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer newCustomerData, RedirectAttributes redirectAttributes) {
        newCustomerData.setId(id);
        try {
            customerService.updateCustomer(id, newCustomerData);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente actualizado exitosamente.");
        } catch (RelationNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Cliente no encontrado.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al actualizar el cliente.");
        }
        return "redirect:/Customers";
    }

    /**
     * Elimina un cliente y redirige a la lista de clientes.
     *
     * @param id                  ID del cliente a eliminar.
     * @param redirectAttributes   Atributos para redireccionar.
     * @return Redirección a la lista de clientes.
     */
    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            customerService.deleteCustomer(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el cliente.");
        }
        return "redirect:/Customers";
    }
}


