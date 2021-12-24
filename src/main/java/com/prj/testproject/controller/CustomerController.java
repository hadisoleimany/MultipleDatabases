package com.prj.testproject.controller;

import com.prj.testproject.entity.Customer;
import com.prj.testproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/save")
    public Customer save(@RequestBody Customer customer) {
        service.save(customer);
        return customer;
    }

    @GetMapping("/v1")
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll());
    }
}
