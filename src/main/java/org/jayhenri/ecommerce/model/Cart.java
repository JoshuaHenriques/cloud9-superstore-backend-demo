package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    @Column(nullable = true)
    private ArrayList<Item> items;

    @Column(nullable = true)
    private String customerEmail;

    @Column(nullable = true)
    private Double total;

    public Cart(ArrayList<Item> items, String customerEmail, Double total) {
        this.items = items;
        this.customerEmail = customerEmail;
        this.total = total;
    }
}