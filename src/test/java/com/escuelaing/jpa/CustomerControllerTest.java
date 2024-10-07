package com.escuelaing.jpa;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.escuelaing.jpa.controller.CustomerController;
import com.escuelaing.jpa.model.Customer;
import com.escuelaing.jpa.service.CustomerService;

import java.util.List;
import java.util.Collections;

class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testListCustomers() throws Exception {
        List<Customer> customers = Collections.singletonList(new Customer("John", "Doe", "123 Main St", 100, 200, "Description"));
        Page<Customer> customerPage = new PageImpl<>(customers);

        when(customerService.getCustomers(0, 10)).thenReturn(customerPage);

        mockMvc.perform(get("/Customers"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("customers"))
            .andExpect(model().attribute("currentPage", 0))
            .andExpect(view().name("index"));
    }

    @Test
    void testGetAllCustomers() throws Exception {
        List<Customer> customers = Collections.singletonList(new Customer("John", "Doe", "123 Main St", 100, 200, "Description"));

        when(customerService.getAllCustomer()).thenReturn(customers);

        mockMvc.perform(get("/Customers/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testCreateCustomer() throws Exception {
        mockMvc.perform(get("/Customers/createCustomer"))
            .andExpect(status().isOk())
            .andExpect(view().name("create"));
    }

    @Test
    void testCreateCustomerPost() throws Exception {
        Customer customer = new Customer("John", "Doe", "123 Main St", 100, 200, "Description");

        mockMvc.perform(post("/Customers/createCustomer/create")
                .param("firstName", "John")
                .param("lastName", "Doe")
                .param("address", "123 Main St")
                .param("price", "100")
                .param("size", "200")
                .param("description", "Description"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/Customers"));

        verify(customerService, times(1)).saveCustomer(any(Customer.class));
    }

    @Test
    void testUpdateCustomer() throws Exception {
        Long customerId = 1L;
        Customer customer = new Customer("John", "Doe", "123 Main St", 100, 200, "Description");

        mockMvc.perform(post("/Customers/update/{id}", customerId)
                .param("firstName", "Jane")
                .param("lastName", "Doe")
                .param("address", "456 Main St")
                .param("price", "150")
                .param("size", "250")
                .param("description", "New Description"))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/Customers"));

        verify(customerService, times(1)).updateCustomer(eq(customerId), any(Customer.class));
    }

    @Test
    void testDeleteCustomer() throws Exception {
        Long customerId = 1L;

        mockMvc.perform(post("/Customers/delete/{id}", customerId))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/Customers"));

        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}

