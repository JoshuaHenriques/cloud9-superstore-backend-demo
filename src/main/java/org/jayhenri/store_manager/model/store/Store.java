package org.jayhenri.store_manager.model.store;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.inventory.StoreInventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Employee> employee;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<StoreInventory> inventory;

    /**
     * Instantiates a new Store.
     *
     * @param storeName       the store name
     * @param address         the address
     * @param storeInventory  the store inventory
     * @param onlineInventory the online inventory
     */
    public Store(String storeName, Address address) {
        this.storeName = storeName;
        this.address = address;
    }
}
