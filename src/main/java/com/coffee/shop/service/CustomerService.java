package com.coffee.shop.service;

import com.coffee.shop.exception.CustomerNotFound;
import com.coffee.shop.model.Customer;
import com.coffee.shop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepo;
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public Customer findById(Long aLong) {
        return customerRepo.findById(aLong).orElseThrow(CustomerNotFound::new);
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public void deleteById(Long aLong) {

        Customer customerToDelete = this.findById(aLong);
        customerRepo.deleteById(aLong);
    }

    public <S extends Customer> S save(S entity) {
        return customerRepo.save(entity);
    }

    public Customer update(Long id, String name, int age) {
        Customer customer = this.findById(id);
        customer.setName(name);
        customer.setAge(age);
        return customerRepo.save(customer);
    }
}
