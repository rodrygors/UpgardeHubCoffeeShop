package com.coffee.shop.controller.response;

import lombok.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResponse {
    private Long id;
    private Boolean isPaid;
    private CustomerResponse customerResponse;
}
