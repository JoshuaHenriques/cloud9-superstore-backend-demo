package org.jayhenri.ecommerce.model.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.ecommerce.model.store.Item;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store_inventory")
public class StoreInventory implements Serializable {

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
}
