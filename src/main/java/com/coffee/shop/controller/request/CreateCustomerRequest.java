package com.coffee.shop.controller.request;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCustomerRequest {
    private String name;
    private int age;
}
