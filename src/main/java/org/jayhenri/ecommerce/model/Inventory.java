package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Inventory.
 */
// TODO: Abstract for different type of items
// TODO: Implement ratings
// TODO: Implement comments
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column
    private String productName;

    @Column
    private int quantity;

    @Column
    private Item item;

    /**
     * Instantiates a new Inventory.
     *
     * @param productName the product name
     * @param quantity    the quantity
     * @param item        the item
     */
    public Inventory(String productName, int quantity, Item item) {
        this.productName = productName;
        this.quantity = quantity;
        this.item = item;
    }
}
