package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.ClothingInventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClothingInventoryRepository extends JpaRepository<ClothingInventory, UUID> {
    @Query("select case when count(c)> 0 then true else false end from ClothingInventory c where lower(c.productName) like lower(:productName)")
    boolean existsByProductName(@Param("productName") String name);
}