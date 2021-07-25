package org.jayhenri.ecommerce.repository;

import java.util.UUID;

import org.jayhenri.ecommerce.model.OrderDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order db repository.
 */
@Repository
public interface OrderDBRepository extends JpaRepository<OrderDB, UUID> {

}