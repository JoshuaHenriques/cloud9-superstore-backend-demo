package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Inventory repository.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

}