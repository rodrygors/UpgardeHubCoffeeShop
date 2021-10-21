package com.coffee.shop.service;

import com.coffee.shop.controller.request.CreateCoffeeRequest;
import com.coffee.shop.exception.CoffeeNotFound;
import com.coffee.shop.exception.PurchaseNotFound;
import com.coffee.shop.model.Coffee;
import com.coffee.shop.model.Purchase;
import com.coffee.shop.repository.CoffeeRepository;
import com.coffee.shop.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private final PurchaseRepository purchaseRepository;

    public CoffeeService(CoffeeRepository coffeeRepository, PurchaseService purchaseService, PurchaseRepository purchaseRepository) {
        this.coffeeRepository = coffeeRepository;
        this.purchaseRepository = purchaseRepository;
    }

    //Create coffee
    public Coffee createCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    //get all coffees
    public List<Coffee> getAllCoffees() {
        return coffeeRepository.findAll();
    }

    //get Coffee by Id
    public Coffee getCoffeeById(Long id) {
        return coffeeRepository.findById(id).orElseThrow(CoffeeNotFound::new);
    }

    //get Purchase by Id
    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(PurchaseNotFound::new);
    }

    //add Coffee To Purchase
    public Coffee addCoffeeToPurchase(Long purchaseId, Long coffeeId) {
        Coffee coffee = this.getCoffeeById(coffeeId);
        Purchase purchase = this.getPurchaseById(purchaseId);
        purchase.getPurchase_coffee().add(coffee);
        return coffee;
    }

    //remove Coffee From Purchase
    public void removeCoffeeFromPurchase(Long purchaseId, Long coffeeId) {
        Coffee coffee = getCoffeeById(coffeeId);
        Purchase purchase = this.getPurchaseById(purchaseId);
        purchase.getPurchase_coffee().remove(coffee);
    }

    // update coffee
    public Coffee updateCoffee(Long id, CreateCoffeeRequest createCoffeeRequest) {
        Coffee coffeeToEdit = getCoffeeById(id);
        coffeeToEdit.setType(createCoffeeRequest.getType());
        coffeeToEdit.setName(createCoffeeRequest.getName());
        coffeeToEdit.setPrice(createCoffeeRequest.getPrice());
        return coffeeToEdit;
    }

    //delete Coffee
    public void deleteCoffeeById(Long aLong) {
        Coffee coffeeToDelete = this.getCoffeeById(aLong);

        if (!coffeeToDelete.getPurchases().isEmpty()) {
            for (Purchase purchase : coffeeToDelete.getPurchases()) {
                if (!purchase.getPurchase_coffee().isEmpty())
                    purchase.getPurchase_coffee().removeIf(coffee -> coffee.getId() == coffeeToDelete.getId());
            }
        }

        coffeeRepository.deleteById(coffeeToDelete.getId());
    }
}
