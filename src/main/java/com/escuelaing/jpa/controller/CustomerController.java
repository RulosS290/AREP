package com.escuelaing.jpa.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String home(Model model) {
        List<Customer> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        return "index";
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }

    // Crear un nuevo cliente
    @PostMapping("/create")
    public String createCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/Customers";
    }

    // Actualizar un cliente
    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute Customer newCustomerData) {
        newCustomerData.setId(id); // Asegúrate de que el id se establezca en el objeto
        try {
            customerService.updateCustomer(id, newCustomerData);
        } catch (RelationNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/Customers";
    }

    // Eliminar un cliente
    @PostMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/Customers";
    }
}
