package com.coffee.shop.model;

import com.coffee.shop.controller.response.PurchaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "purchase_coffee",
            joinColumns = @JoinColumn(name = "purchaseId"),
            inverseJoinColumns = @JoinColumn(name = "coffeeId"))
    private List<Coffee> purchase_coffee = new ArrayList<>();


//    @JsonIgnore
//    public List<PurchaseResponse> getPurchaseCoffeeResponse(){
//        if(!this.purchase_coffee.isEmpty()){
//            for(Coffee coffee : this.purchase_coffee){
//
//            }
//        }
//        return new ArrayList<>();
//    }
}
