package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// TODO: Update Customer through CustomerService methods
@Service
@NoArgsConstructor
public class OrderDBService {

    @Autowired
    private OrderDBRepository orderDBRepository;

    public void addOrderToDB(OrderDB orderDB) {
        orderDBRepository.save(orderDB);
    }
}
