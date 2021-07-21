package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

// TODO: Implement HST+GST+PROMOCODE computation

/**
 * The type Cart.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    @Id
    @Column(nullable = false)
    private UUID cartUUID = UUID.randomUUID();

    @JoinColumn(name = "itemUUID", insertable=false, updatable=false)
    @OneToMany
    private List<Item> items;

    @JoinColumn(name = "customerUUID", nullable = false, insertable=false, updatable=false)
    @OneToOne
    private Customer customer;

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
    public Cart(List<Item> items, String customerEmail, Double total) {
        this.items = items;
        this.customerEmail = customerEmail;
        this.total = total;
    }
}