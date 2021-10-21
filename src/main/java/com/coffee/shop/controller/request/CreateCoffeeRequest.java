package com.coffee.shop.controller.request;

import com.coffee.shop.model.CoffeeType;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCoffeeRequest {
    private CoffeeType type;
    private String name;
    private float price;
}
