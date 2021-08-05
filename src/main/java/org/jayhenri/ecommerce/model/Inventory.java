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
    @Column(name = "item_id", nullable = false)
    private UUID itemUUID = UUID.randomUUID();

    @OneToOne
    @MapsId
    @JoinColumn(name = "item_id", nullable = true, unique = true)
    private Item item;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Instantiates a new Inventory.
     *
     * @param quantity    the quantity
     */
    public Inventory(int quantity) {
        this.quantity = quantity;
    }
}
