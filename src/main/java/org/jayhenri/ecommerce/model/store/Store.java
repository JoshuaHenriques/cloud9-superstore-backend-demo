package org.jayhenri.ecommerce.model.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.ecommerce.model.customer.Address;
import org.jayhenri.ecommerce.model.inventory.OnlineInventory;
import org.jayhenri.ecommerce.model.inventory.StoreInventory;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "store")
public class Store {

    @Id
    @Column(name = "store_id", nullable = false)
    private UUID storeUUID = UUID.randomUUID();

    @Column(name = "store_name", nullable = false, unique = true)
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
}
