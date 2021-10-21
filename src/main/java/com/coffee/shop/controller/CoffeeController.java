package com.coffee.shop.controller;

import com.coffee.shop.controller.request.CreateCoffeeRequest;
import com.coffee.shop.exception.CoffeeNotFound;
import com.coffee.shop.model.Coffee;
import com.coffee.shop.service.CoffeeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class CoffeeController {

    CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }
//get all coffees
    @GetMapping("/coffees")
    public List<Coffee> getAllCoffees(){
        return coffeeService.getAllCoffees();
    }
    //get coffee by Id
    @GetMapping("/coffees/{id}")
    public Coffee getCoffeeById(@PathVariable Long id){
        return coffeeService.getCoffeeById(id);
    }
    //create Coffee
    @PostMapping(value = "/coffees", consumes = "application/json")
    public Coffee createCoffee(@RequestBody @Valid CreateCoffeeRequest createCoffeeRequest){
        return coffeeService.createCoffee(createCoffeeRequest);
    }
    //add coffee to purchase
    @PostMapping(value = "/coffees/purchases", consumes = "application/json")
    public Coffee addCoffeeToPurchase(@RequestBody @Valid Long purchaseId, Long coffeeId){
        return coffeeService.addCoffeeToPurchase(purchaseId, coffeeId);
    }
    //update Coffee
    @PutMapping("/coffees/{id}")
    public void updateCoffee(@PathVariable Long id,@RequestBody @Valid CreateCoffeeRequest coffeeToEdit){
        coffeeService.updateCoffee(id, coffeeToEdit);
    }
    //remove coffee from purchase
    @DeleteMapping("/coffees/{id}")
    public void removeCoffeeFromPurchase(@PathVariable Long purchaseId, Long coffeeId){
        coffeeService.removeCoffeeFromPurchase(purchaseId, coffeeId);
    }

    @DeleteMapping("/coffees/{id}")
    public void deleteCoffee(@PathVariable Long id){
        coffeeService.deleteCoffee(id);
    }
}
