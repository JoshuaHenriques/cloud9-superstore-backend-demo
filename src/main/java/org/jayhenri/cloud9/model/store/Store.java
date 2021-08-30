package org.jayhenri.cloud9.model.store;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.customer.Address;

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
    @JoinColumn(name = "address_id", nullable = false, unique = true)
    private Address address;

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
