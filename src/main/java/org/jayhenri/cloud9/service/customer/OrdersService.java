package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;

import java.util.Set;
import java.util.UUID;

/**
 * The type Orders service.
 */
public class OrdersService {

    /**
     * Add order.
     *
     * @param customer     the customer
     * @param orders the order
     */
    public Customer addOrder(Customer customer, Orders orders) {

        customer.getOrders().add(orders);
        return customer;
    }

    /**
     * Update order.
     *
     * @param customer    the customer
     * @param uuid        the uuid
     * @param orderStatus the order status
     */
    public Customer updateOrder(Customer customer, UUID uuid, String orderStatus) {

        customer.getOrders().forEach(order -> {
            if (order.getOrdersUUID().equals(uuid)) order.setOrderStatus(orderStatus);
        });
        return customer;
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
}
