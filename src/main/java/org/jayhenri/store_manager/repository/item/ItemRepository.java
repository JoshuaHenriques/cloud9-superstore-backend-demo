package org.jayhenri.store_manager.repository.item;

import org.jayhenri.store_manager.model.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, UUID> {

    /**
     * Exists by phone number boolean.
     *
     * @param itemName the item name
     * @return the boolean
     */
    @Query(value = "SELECT * FROM item WHERE EXISTS (SELECT item_name from item WHERE item.item_name = :item_name", nativeQuery = true)
    boolean existsByItemName(@Param("item_name") String itemName);


    /**
     * Gets by email.
     *
     * @param itemName the item name
     * @return the by email
     */
    @Query(value = "SELECT * FROM item WHERE item.item_name = :item_name", nativeQuery = true)
    Item getByItemName(@Param("item_name") String itemName);
// todo
//    /**
//     * Exists by phone number boolean.
//     *
//     * @param itemName the item name
//     * @return the boolean
//     */
//    @Query(value = "", nativeQuery = true)
//    boolean existsByReviewId(@Param("reviewId") UUID reviewId, @Param("itemId") UUID itemId);
}