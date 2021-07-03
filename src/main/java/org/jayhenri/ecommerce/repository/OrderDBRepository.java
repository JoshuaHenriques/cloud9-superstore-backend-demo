package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.Customer;
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

    @Query(value = "SELECT * FROM customers WHERE customers.email=:email", nativeQuery = true)
    Customer getByEmail(@Param("email") String email);
}