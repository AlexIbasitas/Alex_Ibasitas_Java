package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return repo.findAll();
    }

    // GET route to find a customer record by id
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        Optional<Customer> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    // GET route to find customer records by state
    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return repo.findByState(state);
    }

    // POST route to create new customer
    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer (@RequestBody Customer customer) {
        return repo.save(customer);
    }

    // PUT route to update customer
    @PutMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer) {
        repo.save(customer);
    }

    // DELETE route to delete an existing customer
    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        repo.deleteById(id);
    }
}
