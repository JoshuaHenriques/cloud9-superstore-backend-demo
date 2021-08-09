package org.jayhenri.ecommerce.model.customer;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.jayhenri.ecommerce.model.store.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The type Cart.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
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
    private Set<Item> cart;

    @Column(name = "total", nullable = true)
    private Double total;

    /**
     * Instantiates a new Cart.
     *
     * @param total         the total
     */
    public Cart(Double total) {
        this.total = total;
    }
}