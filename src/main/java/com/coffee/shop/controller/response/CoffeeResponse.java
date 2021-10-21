package com.coffee.shop.controller.response;

import com.coffee.shop.model.CoffeeType;
import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeResponse {
    private Long id;
    private CoffeeType type;
    private String name;
    private float price;
}
