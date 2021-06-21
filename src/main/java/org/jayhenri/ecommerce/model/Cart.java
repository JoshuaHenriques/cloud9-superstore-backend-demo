package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    private ArrayList<Item> items;

    private Double subTotal;

    private Double total;

    private final Double HST = 0.13;
}