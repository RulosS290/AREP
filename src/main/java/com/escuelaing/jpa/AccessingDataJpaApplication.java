package com.escuelaing.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.escuelaing.jpa.model.Customer;
import com.escuelaing.jpa.repository.CustomerRepository;

@SpringBootApplication
public class AccessingDataJpaApplication {

  private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AccessingDataJpaApplication.class);
  }

  @Bean
  public CommandLineRunner demo(CustomerRepository repository) {
    return (args) -> {
      // save a few customers
      repository.save(new Customer("Jack", "Bauer", "Some Address", 100, 30, "CTU agent"));
      repository.save(new Customer("Chloe", "O'Brian", "Some Address", 150, 25, "Computer expert"));
      repository.save(new Customer("Kim", "Bauer", "Some Address", 120, 20, "Jack's daughter"));
      repository.save(new Customer("David", "Palmer", "Some Address", 200, 35, "Former president"));
      repository.save(new Customer("Michelle", "Dessler", "Some Address", 180, 28, "CTU agent"));



      // fetch all customers
      log.info("Customers found with findAll():");
      log.info("-------------------------------");
      repository.findAll().forEach(customer -> {
        log.info(customer.toString());
      });
      log.info("");

      // fetch an individual customer by ID
      Customer customer = repository.findById(1);
      log.info("Customer found with findById(1):");
      log.info("--------------------------------");
      log.info(customer.toString());
      log.info("");

      // fetch customers by last name
      log.info("Customer found with findByLastName('Bauer'):");
      log.info("--------------------------------------------");
      repository.findByLastName("Bauer").forEach(bauer -> {
        log.info(bauer.toString());
      });
      log.info("");
    };
  }
}