package com.escuelaing.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CustomerController {
    private final CustormerService custormerService;

    @Autowired
    public CustomerController(CustormerService custormerService){
        this.custormerService = custormerService;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/Customer")
    @ResponseBody
    public List<Customer> getAllCustomer(){
        return custormerService.getAllCustomer();
    }
    
}
