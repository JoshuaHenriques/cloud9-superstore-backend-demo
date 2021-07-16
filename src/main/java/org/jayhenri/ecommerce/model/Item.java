package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Item extends AuditModel implements Serializable {

    private static final long serialVersionUID = -496088096515099704L;

    @Id
    @Column(nullable = false)
    private UUID itemUUID = UUID.randomUUID();

    @Column
    private String itemName;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cartUUID", nullable = false, insertable=false, updatable=false)
    private Cart cart;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventoryUUID", nullable = false, insertable=false, updatable=false)
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
        this.itemName = productName;
        this.description = description;
        this.price = price;
        this.image = null;
    }
}
