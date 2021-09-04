package org.jayhenri.store_manager.repository.inventory;

import org.jayhenri.store_manager.model.inventory.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Store inventory repository.
 */
@Repository
@Transactional
public interface StoreInventoryRepository extends JpaRepository<StoreInventory, UUID> {


    /**
     * Exists by item name boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    @Query(value = "SELECT * FROM store_inventory WHERE EXISTS (SELECT item_name FROM store_inventory WHERE store_inventory.item_name = :item_name)", nativeQuery = true)
    boolean existsByItemName(@Param("item_name") String phoneNumber);


    /**
     * Gets by item name.
     *
     * @param email the email
     * @return the by item name
     */
    @Query(value = "SELECT * FROM store_inventory WHERE store_inventory.item_name = :item_name)", nativeQuery = true)
    StoreInventory getByItemName(@Param("item_name") String email);
}