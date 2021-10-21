package com.coffee.shop.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated
    private CoffeeType type;
    private String name;
    private float price;

    @ManyToMany(mappedBy = "purchase_coffee")
    private List<Purchase> purchases;
}
