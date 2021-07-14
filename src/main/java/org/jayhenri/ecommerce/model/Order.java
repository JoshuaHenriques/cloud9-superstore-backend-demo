package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The type Order.
 */
// TODO: Fix enum import
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Order implements Serializable {

    private static final long serialVersionUID = -3543368529566294417L;

    @Id
    @Column
    private UUID uuid = UUID.randomUUID();

    @Column
    private String orderStatus;

    @Column
    private String customerEmail;

    @Column
    @OneToMany
    private ArrayList<Item> order;

    @Column
    private double totalPrice;

    /**
     * Instantiates a new Order.
     *
     * @param orderStatus   the order status
     * @param customerEmail the customer email
     * @param order         the order
     * @param totalPrice    the total price
     */
    public Order(String orderStatus, String customerEmail, ArrayList<Item> order, double totalPrice) {
        this.orderStatus = orderStatus;
        this.customerEmail = customerEmail;
        this.order = order;
        this.totalPrice = totalPrice;
    }
}
