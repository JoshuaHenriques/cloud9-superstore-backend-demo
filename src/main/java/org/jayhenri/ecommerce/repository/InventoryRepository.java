package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    @Query("select case when count(c)> 0 then true else false end from Inventory c where lower(c.productName) like lower(:productName)")
    boolean existsByProductName(@Param("productName") String name);

    @Query(value = "SELECT * FROM inventory WHERE inventory.product_Name=:productName", nativeQuery = true)
    Inventory getByProductName(@Param("productName") String productName);
}