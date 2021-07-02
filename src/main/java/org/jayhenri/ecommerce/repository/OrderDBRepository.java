package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface OrderDBRepository extends JpaRepository<OrderDB, UUID> {

    @Query("select case when count(c)> 0 then true else false end from Inventory c where lower(c.productName) like lower(:productName)")
    boolean existsByProductName(@Param("productName") String name);

    @Query(value = "SELECT * FROM inventory WHERE inventory.product_Name=:productName", nativeQuery = true)
    Inventory getByProductName(@Param("productName") String productName);
}