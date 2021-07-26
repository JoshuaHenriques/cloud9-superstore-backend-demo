package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Order db service.
 */
@Service
public class OrderDBService {

    private final OrderDBRepository orderDBRepository;

    /**
     * Instantiates a new Order db service.
     *
     * @param orderDBRepository the order db repository
     */
    @Autowired
    public OrderDBService(OrderDBRepository orderDBRepository) {
        this.orderDBRepository = orderDBRepository;
    }

    /**
     * Add order to db.
     *
     * @param orderDB the order db
     */
    public void addOrderToDB(OrderDB orderDB) {
        orderDBRepository.save(orderDB);
    }
}
