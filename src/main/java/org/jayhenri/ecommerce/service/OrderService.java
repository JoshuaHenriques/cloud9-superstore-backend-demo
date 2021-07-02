package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Cart;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Order;
import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.repository.OrderDBRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

// TODO: Update Customer through CustomerService methods
public class OrderService {

    private Order order;
    private OrderDB orderDB;

    @Autowired
    private CustomerService customerService;

    private static final Double HST = 0.13;
    private static final Double DELIVERY_FEE = 9.99;

    @Autowired
    private OrderDBRepository orderDBRepository;

    private OrderService(Cart cart) {
        order = new Order();
        this.order.setCustomerEmail(cart.getCustomerEmail());
        this.order.setOrder(cart.getItems());
        this.order.setOrderStatus("PROCESSING");
        this.order.setSubTotal(cart.getTotal());
        this.order.setTotalPrice(cart.getTotal() * HST + DELIVERY_FEE);
    }

    public void saveToDB(){
        this.orderDB = (OrderDB) this.order;
        orderDBRepository.save(orderDB);
    }

    public void saveToCustomer(Customer customer) {
        customer.getOrders().add(this.order);
    }
}
