package com.coffee.shop.service;

import com.coffee.shop.controller.repository.CoffeeRepository;
import com.coffee.shop.controller.request.CreateCoffeeRequest;
import com.coffee.shop.exception.CoffeeNotFound;
import com.coffee.shop.model.Coffee;
import com.coffee.shop.model.CoffeeType;
import com.coffee.shop.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CoffeeService {
    @Autowired//Connection to the repository
    private final CoffeeRepository coffeeRepository;

    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }
    //Create coffee
    public Coffee createCoffee(CreateCoffeeRequest createCoffeeRequest){
        String name = createCoffeeRequest.getName();
        CoffeeType type = createCoffeeRequest.getType();
        Float price = createCoffeeRequest.getPrice();
        Coffee coffee = Coffee
                .builder()
                .name(name)
                .type(type)
                .price(price)
                .build();
        return coffeeRepository.save(coffee);
    }
    //get all coffees
    public List<Coffee> getAllCoffees(){
        return coffeeRepository.findAll();
    }
    //get Coffee by Id
    public Coffee getCoffeeById(Long id){
        return coffeeRepository.findById(id).orElseThrow(CoffeeNotFound::new);
    }
    //add Coffee To Purchase
    public Coffee addCoffeeToPurchase(Long purchaseId, Long coffeeId){
        Coffee coffee = getCoffeeById(coffeeId);
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        purchase.addingCoffee(coffee);
        return coffee;
    }
    //remove Coffee From Purchase
    public Coffee removeCoffeeFromPurchase(Long purchaseId, Long coffeeId){
        Coffee coffee = getCoffeeById(coffeeId);
        Purchase purchase = purchaseService.getPurchaseById(purchaseId);
        purchase.removingCoffee(coffee);
        return coffee;
    }
    // update coffee
    public Coffee updateCoffee(Long id, CreateCoffeeRequest createCoffeeRequest){
        Coffee coffeeToEdit = getCoffeeById(id);
        coffeeToEdit.setType(createCoffeeRequest.getType());
        coffeeToEdit.setName(createCoffeeRequest.getName());
        coffeeToEdit.setPrice(createCoffeeRequest.getPrice());
        return coffeeToEdit;
    }
    //delete Coffee
    public Coffee deleteCoffee(Long id){
        Coffee coffee = getCoffeeById(id);
        coffeeRepository.deleteById(id);
        return coffee;
    }
}
