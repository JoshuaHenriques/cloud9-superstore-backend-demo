package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.util.ArrayList;

// TODO: Fix enum import
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Order {

    @Column
    private String orderStatus;

    @Column
    private ArrayList<Item> order;

    @Column
    private double subTotal;

    @Column
    private double totalPrice;

}
