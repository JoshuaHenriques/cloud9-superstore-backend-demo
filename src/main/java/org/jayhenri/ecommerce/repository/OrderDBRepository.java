package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * The interface Order db repository.
 */
@Repository
public interface OrderDBRepository extends JpaRepository<OrderDB, UUID> {

}