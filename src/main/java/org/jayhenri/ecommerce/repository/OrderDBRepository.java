package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.OrderDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface OrderDBRepository extends JpaRepository<OrderDB, UUID> {

}