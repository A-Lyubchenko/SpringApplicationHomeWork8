package com.lyubchenko.springbootapplicationhomework8.service;

import com.lyubchenko.springbootapplicationhomework8.domain.Customer;
import com.lyubchenko.springbootapplicationhomework8.repositories.CustomerRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements Crud<Customer> {
    @Autowired
    private CustomerRepositories customerRepositories;


    @Override
    public void create(Customer entity) {
        customerRepositories.save(entity);
    }

    @Override
    public List<Customer> read() {
        return customerRepositories.findAll();
    }

    @Override
    public void update(Customer entity) {
        customerRepositories.save(entity);
    }

    @Override
    public void deleteEntity(Customer entity) {
        customerRepositories.delete(entity);
    }

    @Override
    public Customer getEntityById(UUID id) {
        Optional<Customer> byId = customerRepositories.findById(id);
        return byId.orElseGet(byId::get);
    }
}
