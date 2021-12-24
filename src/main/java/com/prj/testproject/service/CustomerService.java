package com.prj.testproject.service;

import com.prj.testproject.entity.Customer;
import com.prj.testproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository rep;

    public void save(Customer customer){
        rep.save(customer);
    }

    public List<Customer> getAll() {
        return rep.findAll();
    }
}
