package org.jayhenri.cloud9.model.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The type Store.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "store")
public class Store implements Serializable {

    @Id
    @Column(name = "store_id", nullable = false)
    private UUID storeUUID = UUID.randomUUID();

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "store_inventories",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "store_inventory_id")
    )
    private Set<StoreInventory> storeInventory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "online_inventories",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "online_inventory_id")
    )
    private Set<OnlineInventory> onlineInventory;

    /**
     * Instantiates a new Store.
     *
     * @param storeName       the store name
     * @param address         the address
     * @param storeInventory  the store inventory
     * @param onlineInventory the online inventory
     */
    public Store(String storeName, Address address, Set<StoreInventory> storeInventory, Set<OnlineInventory> onlineInventory) {
        this.storeName = storeName;
        this.address = address;
        this.storeInventory = storeInventory;
        this.onlineInventory = onlineInventory;
    }
}
