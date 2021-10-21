package com.coffee.shop.controller.response;

import lombok.*;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private int age;
}
