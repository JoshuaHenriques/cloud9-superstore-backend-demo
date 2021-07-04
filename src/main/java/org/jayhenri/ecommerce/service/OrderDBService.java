package org.jayhenri.ecommerce.service;

import lombok.NoArgsConstructor;
import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.model.Cart;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.InvalidNameException;
import java.util.ArrayList;

// TODO: Update Customer through CustomerService methods
@Service
@NoArgsConstructor
public class OrderDBService {

    @Autowired
    private CustomerService customerService;

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 9.99;

    @Autowired
    private OrderDBRepository orderDBRepository;

    public void addOrderToDB(Cart cart) {
        OrderDB orderDB = new OrderDB(
                "PROCESSING",
                cart.getCustomerEmail(),
                cart.getItems(),
                cart.getTotal(),
                cart.getTotal()*HST+DELIVERY_FEE
        );
        orderDBRepository.save(orderDB);
    }
}
