package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Item.
 */
// TODO: Implement Ratings
// TODO: There must be a better way to do this
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    @Id
    @Column(nullable = false)
    private UUID itemUUID = UUID.randomUUID();

    @Column
    private String itemName;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cartUUID", nullable = true, insertable = false, updatable = false)
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventoryUUID", nullable = true, insertable = false, updatable = false)
    private Inventory inventory;

    @Column
    private double price;

    @Column
    private byte[] image;

    /**
     * Instantiates a new Item.
     *
     * @param productName the product name
     * @param description the description
     * @param price       the price
     */
    public Item(String productName, String description, double price) {
        this.itemUUID = UUID.randomUUID();
        this.itemName = productName;
        this.description = description;
        this.price = price;
        this.image = null;
    }
}
