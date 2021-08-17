package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.OrderServiceI;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.repository.customer.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

/**
 * The type Orders service.
 */
@Service
public class OrdersService implements OrderServiceI {

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

    /**
     * Add order.
     *
     * @param customer the customer
     * @param orders   the order
     */
    public void add(Customer customer, Orders orders) {

        customer.getOrders().add(orders);
        customerService.update(customer);
    }

    /**
     * Update order.
     *
     * @param orders the orders
     */
    public void update(Orders orders) {

        ordersRepository.save(orders);
    }

    /**
     * Find all orderDetails list.
     *
     * @param customer the email
     * @return the list
     */
    public Set<Orders> findAll(Customer customer) {

        return customer.getOrders();
    }

    /**
     * Exists by email boolean.
     *
     * @param cardId the card id
     * @return the boolean
     */
    public boolean existsById(UUID cardId) {

        return ordersRepository.existsById(cardId);
    }

    /**
     * Gets by email.
     *
     * @param cardId the card id
     * @return the by email
     */
    public Orders getById(UUID cardId) {

        return ordersRepository.getById(cardId);
    }
}
