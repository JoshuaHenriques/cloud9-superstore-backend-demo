package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Embeddable;

// TODO: Implement HST+GST+PROMOCODE computation

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    private ArrayList<Item> items;

    private String customerEmail;

    private Double total;

    public Cart(ArrayList<Item> items, String customerEmail, Double total) {
        this.items = items;
        this.customerEmail = customerEmail;
        this.total = total;
    }
}