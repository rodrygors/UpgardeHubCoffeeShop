package com.coffee.shop.service;

import com.coffee.shop.exception.CustomerNotFound;
import com.coffee.shop.exception.PurchaseNotFound;
import com.coffee.shop.model.Coffee;
import com.coffee.shop.model.Customer;
import com.coffee.shop.model.Purchase;
import com.coffee.shop.repository.CoffeeRepository;
import com.coffee.shop.repository.CustomerRepository;
import com.coffee.shop.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final CoffeeRepository coffeeRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, CustomerRepository customerRepository, CoffeeRepository coffeeRepository) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.coffeeRepository = coffeeRepository;
    }

    public List<Purchase> findAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(PurchaseNotFound::new);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(CustomerNotFound::new);
    }
    public Purchase addPurchase(Long customerId, Purchase purchase) {
        Customer customer = this.findCustomerById(customerId);
        purchase.setCustomer(customer);
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Long purchaseId, Boolean isPaid, Long customerId) {
        Purchase purchase = this.findById(purchaseId);
        purchase.setCustomer(this.findCustomerById(customerId));
        purchase.setIsPaid(isPaid);
        purchaseRepository.save(purchase);
        return purchase;
    }

    public void deleteById(Long id) {
        Purchase purchase = this.findById(id);

        if(!purchase.getPurchase_coffee().isEmpty()){
            for(Coffee coffee : purchase.getPurchase_coffee()){
                coffeeRepository.deleteById(coffee.getId());
            }
        }
        purchaseRepository.deleteById(id);
    }
}
