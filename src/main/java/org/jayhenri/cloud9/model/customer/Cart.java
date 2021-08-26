package org.jayhenri.cloud9.model.customer;


import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.item.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Cart.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "cart")
public class Cart implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    @Id
    @Column(name = "cart_id", nullable = false)
    private UUID cartUUID = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items;

    @Column(name = "total")
    private Double total;

    /**
     * Instantiates a new Cart.
     *
     * @param customer the customer
     * @param items    the items
     */
    public Cart(Customer customer, Set<Item> items) {
        this.customer = customer;
        this.items = items;
        this.total = 0.00;
        calculate();
    }

    /**
     * Calculate.
     */
    public void calculate() {

        items.forEach(item -> this.total += item.getPrice());

        double DELIVERY = 9.99;
        double HST = 1.13;

        this.total = Math.round(((total * HST) + DELIVERY) * 100.00) / 100.00;
    }
}