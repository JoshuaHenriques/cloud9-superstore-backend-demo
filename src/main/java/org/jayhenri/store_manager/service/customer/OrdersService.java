package org.jayhenri.store_manager.service.customer;

import org.jayhenri.store_manager.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.OrdersServiceI;
import org.jayhenri.store_manager.model.customer.Customer;
import org.jayhenri.store_manager.model.customer.Orders;
import org.jayhenri.store_manager.repository.customer.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * The type Orders service.
 */
@Service
public class OrdersService implements OrdersServiceI {

    private final CustomerServiceI customerService;
    private final OrdersRepository ordersRepository;

    /**
     * Instantiates a new Orders service.
     *
     * @param ordersRepository the orders repository
     * @param customerService  the customer service
     */
    @Autowired
    public OrdersService(OrdersRepository ordersRepository, CustomerServiceI customerService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
    }

    public void add(Customer customer, Orders orders) {

        customer.getOrders().add(orders);
        customerService.update(customer);
    }

    public void update(Orders orders) {

        ordersRepository.save(orders);
    }

    public Set<Orders> findAll(Customer customer) {

        return customer.getOrders();
    }

    public boolean existsById(UUID cardId) {

        return ordersRepository.existsById(cardId);
    }

    public Orders getById(UUID cardId) {

        return ordersRepository.getById(cardId);
    }
}
