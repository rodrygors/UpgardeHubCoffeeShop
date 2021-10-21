package com.coffee.shop.controller;

import com.coffee.shop.controller.request.CreateCoffeeRequest;
import com.coffee.shop.controller.response.CoffeeResponse;
import com.coffee.shop.exception.CoffeeNotFound;
import com.coffee.shop.model.Coffee;
import com.coffee.shop.service.CoffeeService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
    public List<CoffeeResponse> getAllCoffees(){
        List<Coffee> coffees = coffeeService.getAllCoffees();
        List<CoffeeResponse> coffeeResponseList = new ArrayList<>();

        for(Coffee coffee : coffees){
            coffeeResponseList.add(new CoffeeResponse(
                    coffee.getId(),
                    coffee.getType(),
                    coffee.getName(),
                    coffee.getPrice()
            ));
        }

        return coffeeResponseList;
    }

    //get coffee by Id
    @GetMapping("/coffees/{id}")
    public CoffeeResponse getCoffeeById(@PathVariable Long id){
        Coffee coffee = coffeeService.getCoffeeById(id);
        return new CoffeeResponse(
                coffee.getId(),
                coffee.getType(),
                coffee.getName(),
                coffee.getPrice()
        );
    }

    //create Coffee
    @PostMapping(value = "/coffees", consumes = "application/json")
    public CoffeeResponse createCoffee(@RequestBody @Valid CreateCoffeeRequest createCoffeeRequest){
        Coffee coffee = coffeeService.createCoffee(Coffee.builder()
                .name(createCoffeeRequest.getName())
                .type(createCoffeeRequest.getType())
                .price(createCoffeeRequest.getPrice())
                .build());
        return new CoffeeResponse(
                coffee.getId(),
                coffee.getType(),
                coffee.getName(),
                coffee.getPrice()
        );
    }

    //add coffee to purchase
    @PostMapping(value = "/coffees/purchases", consumes = "application/json")
    public CoffeeResponse addCoffeeToPurchase(@RequestBody @Valid Long purchaseId, Long coffeeId){
        Coffee coffee = coffeeService.addCoffeeToPurchase(purchaseId, coffeeId);
        return new CoffeeResponse(
                coffee.getId(),
                coffee.getType(),
                coffee.getName(),
                coffee.getPrice()
        );
    }

    //update Coffee
    @PutMapping("/coffees/{id}")
    public CoffeeResponse updateCoffee(@PathVariable Long id,@RequestBody @Valid CreateCoffeeRequest coffeeToEdit){
        Coffee coffee = coffeeService.updateCoffee(id, coffeeToEdit);
        return new CoffeeResponse(
                coffee.getId(),
                coffee.getType(),
                coffee.getName(),
                coffee.getPrice()
        );
    }
    //remove coffee from purchase
    @DeleteMapping("/coffees/{id}")
    public void removeCoffeeFromPurchase(@PathVariable Long purchaseId, Long coffeeId){
        coffeeService.removeCoffeeFromPurchase(purchaseId, coffeeId);
    }

    @DeleteMapping("/coffees/{id}")
    public void deleteCoffee(@PathVariable Long id){
        coffeeService.deleteCoffeeById(id);
    }
}
