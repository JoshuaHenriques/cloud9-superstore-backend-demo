package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Update Customer through CustomerService methods
@Service
public class OrderDBService {

    private final OrderDBRepository orderDBRepository;

    @Autowired
    public OrderDBService(OrderDBRepository orderDBRepository) {
        this.orderDBRepository = orderDBRepository;
    }

    public void addOrderToDB(OrderDB orderDB) {
        orderDBRepository.save(orderDB);
    }
}
