package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Inventory.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryUUID = UUID.randomUUID();

    @JoinColumn(name = "item_id", nullable = false, unique = true)
    @OneToOne
    private Item item;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    private double price;

    /**
     * Instantiates a new Inventory.
     *
     * @param quantity    the quantity
     * @param item        the item
     */
    public Inventory(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
