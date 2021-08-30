package org.jayhenri.cloud9.model.inventory;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.store.Store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Store inventory.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "store_inventory")
public class StoreInventory implements Serializable {

    private static final long serialVersionUID = -1112477284611964207L;

    @Id
    @Column(name = "item_id", nullable = false)
    private UUID inventoryUUID = UUID.randomUUID();

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    @Column(name = "quantity", nullable = false, unique = false)
    private int quantity;

    @Column(name = "price", nullable = false)
    private double price;

    @MapsId
    @OneToOne
    @JoinColumn(name="item_id", unique=true, nullable=false)
    private Item item;

    @ManyToOne
    @JoinColumn(name="store_id", nullable=false, updatable=false)
    private Store store;

    /**
     * Instantiates a new Store inventory.
     *
     * @param item     the item
     * @param itemName the item name
     * @param quantity the quantity
     * @param price    the price
     */
    public StoreInventory(Item item, String itemName, int quantity, double price) {
        this.item = item;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}
