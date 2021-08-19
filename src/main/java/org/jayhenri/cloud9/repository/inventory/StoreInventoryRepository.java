package org.jayhenri.cloud9.repository.inventory;

import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
public interface StoreInventoryRepository extends JpaRepository<StoreInventory, UUID> {


    /**
     * Exists by product name boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    @Query(value = "SELECT * FROM store_inventory WHERE EXISTS (SELECT item_name FROM store_inventory WHERE store_inventory.item_name = :item_name)", nativeQuery = true)
    boolean existsByItemName(@Param("item_name") String phoneNumber);


    /**
     * Gets by product name.
     *
     * @param email the email
     * @return the by product name
     */
    @Query(value = "SELECT * FROM store_inventory WHERE store_inventory.item_name = :item_name)", nativeQuery = true)
    StoreInventory getByItemName(@Param("item_name") String email);
}