package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.repository.customer.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.UUID;

/**
 * The type Orders service.
 */
public class OrdersService {

    private final CustomerService customerService;
    private final OrdersRepository ordersRepository;

    /**
     * Instantiates a new Orders service.
     *
     * @param ordersRepository the orders repository
     * @param customerService  the customer service
     */
    @Autowired
    public OrdersService(OrdersRepository ordersRepository, CustomerService customerService) {
        this.ordersRepository = ordersRepository;
        this.customerService = customerService;
    }

    /**
     * Add order.
     *
     * @param customer the customer
     * @param orders   the order
     */
    public void addOrder(Customer customer, Orders orders) {

        customer.getOrders().add(orders);
        customerService.update(customer);
    }

    /**
     * Update order.
     *
     * @param orders the orders
     */
    public void updateOrders(Orders orders) {

        ordersRepository.save(orders);
    }

    /**
     * Find all orderDetails list.
     *
     * @param customer the email
     * @return the list
     */
    public Set<Orders> findAllOrders(Customer customer) {

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
