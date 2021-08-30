package org.jayhenri.cloud9.customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.OrdersServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.repository.customer.OrdersRepository;
import org.jayhenri.cloud9.service.customer.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The type Order db service test.
 */
@ExtendWith(MockitoExtension.class)
class OrdersServiceTest {

    /**
     * The Order db repository.
     */
    @Mock
    private OrdersRepository ordersRepository;

    /**
     * The Order db repository.
     */
    @Mock
    private CustomerServiceI customerService;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    /**
     * The Order db repository.
     */
    private OrdersServiceI ordersService;

    private Customer customer;

    private Orders orders;

    private UUID uuid;

    /**
     * The Argument captor.
     */
    @Captor
    private ArgumentCaptor<Orders> captorOrders;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        ordersService = new OrdersService(ordersRepository, customerService);

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                new Address(),
                new HashSet<>(),
                new HashSet<>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );

        orders = new Orders(
                "PENDING",
                new HashSet<>(),
                293.68,
                customer
        );
    }

    /**
     * Add order.
     */
    @Test
    void addOrder() {

        ordersService.add(customer, orders);

        then(customerService).should().update(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCustomer.getValue().getOrders().contains(orders)).isTrue();
    }

    /**
     * Update order.
     */
    @Test
    void updateOrder() {
        orders.setOrderStatus("COMPLETED");
        ordersService.update(orders);

        then(ordersRepository).should().save(captorOrders.capture());

        assertThat(captorOrders.getValue().getOrderStatus()).isEqualTo("COMPLETED");
        assertThat(orders).isEqualTo(captorOrders.getValue());
    }

    /**
     * Find all orders.
     */
    @Test
    void findAllOrders() {

        Set<Orders> orders = customer.getOrders();
        Set<Orders> findAllOrders = ordersService.findAll(customer);

        assertThat(findAllOrders).isEqualTo(orders);
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(ordersRepository.existsById(uuid)).willReturn(true);

        boolean exists = ordersService.existsById(uuid);

        then(ordersRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(ordersRepository.existsById(uuid)).willReturn(false);

        boolean exists = ordersService.existsById(uuid);

        then(ordersRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(ordersRepository.getById(uuid)).willReturn(orders);

        Orders _orders = ordersService.getById(uuid);

        then(ordersRepository).should().getById(captorUUID.capture());

        assertThat(_orders).isEqualTo(orders);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }
}