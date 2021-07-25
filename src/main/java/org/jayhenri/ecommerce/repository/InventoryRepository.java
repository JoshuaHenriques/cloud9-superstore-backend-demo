package org.jayhenri.ecommerce.repository;

import java.util.UUID;

import org.jayhenri.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Inventory repository.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    /**
     * Exists by product name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    @Query("select case when count(c)> 0 then true else false end from Inventory c where lower(c.productName) like lower(:productName)")
    boolean existsByProductName(@Param("productName") String name);

    /**
     * Gets by product name.
     *
     * @param productName the product name
     * @return the by product name
     */
    @Query(value = "SELECT * FROM inventory WHERE inventory.product_Name=:productName", nativeQuery = true)
    Inventory getByProductName(@Param("productName") String productName);
}