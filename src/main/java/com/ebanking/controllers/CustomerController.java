package com.ebanking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.dao.ClientRepository;
import com.ebanking.models.Client;

@RestController

@RequestMapping("/api/auth")
public class CustomerController {

    @Autowired
    ClientRepository customerRepository;

//    @GetMapping("/api/customer")
//    public List<Customer> findAllCustomers(
//            @RequestParam(name="email",required=false) String email) {
//
//        if (email!=null) {
//            return (List<Customer>)customerRepository.findCustomerByEmail(email);
//        }
//
//        return (List<Customer>)customerRepository.findAll();
//    }
//
    @GetMapping("/customer/{userId}")
    public Client findCustomerById(@PathVariable("userId") int id) {

        return customerRepository.findById(id).orElse(null);
    }


    @PostMapping("/customer")
    public Client createCustomer(@RequestBody Client customer) {

        return customerRepository.save(customer);
    }

}
