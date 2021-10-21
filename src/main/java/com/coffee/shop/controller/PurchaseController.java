package com.coffee.shop.controller;

import com.coffee.shop.controller.request.CreatePurchaseRequest;
import com.coffee.shop.controller.response.CustomerResponse;
import com.coffee.shop.controller.response.PurchaseResponse;
import com.coffee.shop.model.Customer;
import com.coffee.shop.model.Purchase;
import com.coffee.shop.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping(value = "/purchases")
    public List<PurchaseResponse> getAllPurchases() {
        List<Purchase> purchases = purchaseService.findAllPurchases();
        List<PurchaseResponse> purchaseResponseList = new ArrayList<>();
        for (Purchase purchase : purchases) {
            purchaseResponseList.add(
                    new PurchaseResponse(
                            purchase.getId(),
                            purchase.getIsPaid(),
                            new CustomerResponse(
                                    purchase.getCustomer().getId(),
                                    purchase.getCustomer().getName(),
                                    purchase.getCustomer().getAge()
                            )
                    ));
        }
        return purchaseResponseList;
    }

    @GetMapping(value = "/purchases/{id}")
    public PurchaseResponse getPurchaseById(@PathVariable(value = "id") Long id) {
        Purchase purchase = purchaseService.findById(id);
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getIsPaid(),
                new CustomerResponse(
                        purchase.getCustomer().getId(),
                        purchase.getCustomer().getName(),
                        purchase.getCustomer().getAge()
                )
        );
    }

    @PostMapping(value = "/purchases")
    public PurchaseResponse addPurchaseToCustomer(@RequestBody CreatePurchaseRequest purchaseRequest) {
        Purchase purchase = purchaseService.addPurchase(purchaseRequest.getCustomerId(), Purchase.builder()
                .isPaid(purchaseRequest.getIsPaid())
                .build());
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getIsPaid(),
                new CustomerResponse(
                        purchase.getCustomer().getId(),
                        purchase.getCustomer().getName(),
                        purchase.getCustomer().getAge()
                )
        );
    }

    @PutMapping(value = "/purchases/{id}")
    public PurchaseResponse updatePurchase(@RequestBody CreatePurchaseRequest newPurchaseRequest, @PathVariable(value = "id")Long purchaseId) {
        Purchase purchase = purchaseService.updatePurchase(purchaseId, newPurchaseRequest.getIsPaid(), newPurchaseRequest.getCustomerId());
        return new PurchaseResponse(
                purchase.getId(),
                purchase.getIsPaid(),
                new CustomerResponse(
                        purchase.getCustomer().getId(),
                        purchase.getCustomer().getName(),
                        purchase.getCustomer().getAge()
                )
        );
    }

    @DeleteMapping(value = "/purchases/{id}")
    public void deletePurchaseById(@PathVariable(value = "id")Long id){
        purchaseService.deleteById(id);
    }
}
