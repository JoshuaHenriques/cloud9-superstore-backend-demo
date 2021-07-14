package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.*;

// TODO: Implement HST+GST+PROMOCODE computation

/**
 * The type Cart.
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    @Column
    @OneToMany
    private ArrayList<Item> items;

    @Column
    private String customerEmail;

    @Column
    private Double total;

    /**
     * Instantiates a new Cart.
     *
     * @param items         the items
     * @param customerEmail the customer email
     * @param total         the total
     */
    public Cart(ArrayList<Item> items, String customerEmail, Double total) {
        this.items = items;
        this.customerEmail = customerEmail;
        this.total = total;
    }
}