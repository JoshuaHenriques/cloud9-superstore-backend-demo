package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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

    @Column(name = "item_ids", nullable = true)
    private List<Item> items;

    @Column(name = "total", nullable = true)
    private Double total;

    /**
     * Instantiates a new Cart.
     *
     * @param items         the items
     * @param total         the total
     */
    public Cart(List<Item> items, Double total) {
        this.items = items;
        this.total = total;
    }
}