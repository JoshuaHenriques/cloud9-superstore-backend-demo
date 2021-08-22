package org.jayhenri.cloud9.model.inventory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.cloud9.model.item.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Online inventory.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "online_inventory")
public class OnlineInventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column(name = "inventory_id", nullable = false)
    private UUID inventoryUUID = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false, unique = true)
    private Item item;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    /**
     * Instantiates a new Online inventory.
     *
     * @param item     the item
     * @param itemName the item name
     * @param quantity the quantity
     * @param price    the price
     */
    public OnlineInventory(Item item, String itemName, int quantity, double price) {
        this.item = item;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}
