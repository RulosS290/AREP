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

@Controller
@RequestMapping("/Customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String listCustomers(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Customer> customers = customerService.getCustomers(page, 10); // 5 por página
        model.addAttribute("customers", customers.getContent());
        model.addAttribute("totalPages", customers.getTotalPages());
        model.addAttribute("currentPage", page);
        return "index";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/createCustomer")
    public String create(){
        return "create";
    }

    // Crear un nuevo cliente
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

    // Actualizar un cliente
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

    // Eliminar un cliente
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

