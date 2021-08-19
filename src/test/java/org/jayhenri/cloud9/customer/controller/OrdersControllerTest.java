package org.jayhenri.cloud9.customer.controller;

import org.assertj.core.api.AssertionsForClassTypes;
import org.jayhenri.cloud9.controller.customer.OrdersController;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.OrdersNotFoundException;
import org.jayhenri.cloud9.interfaces.service.customer.AddressServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.OrdersServiceI;
import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.naming.InvalidNameException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class OrdersControllerTest {

    @Captor
    private ArgumentCaptor<Orders> captorOrders;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Mock
    private CustomerServiceI customerService;

    @Mock
    private OrdersServiceI ordersService;

    private OrdersController ordersController;

    private Customer customer;

    private Orders orders;

    private UUID uuid;

    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        ordersController = new OrdersController(ordersService, customerService);

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                new Cart(),
                new Address(),
                new HashSet<CreditCard>(),
                new HashSet<Orders>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );

        orders = new Orders(
                "PENDING",
                new HashSet<Item>(),
                293.68
        );
    }

    @Test
    void addOrders() throws InvalidOrdersException {

        given(customerService.getById(uuid)).willReturn(customer);

        ResponseEntity<String> response = ordersController.add(orders, uuid);

        then(ordersService).should().add(captorCustomer.capture(), captorOrders.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorOrders.getValue()).isEqualTo(orders);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void addOrdersThrowsInvalidOrdersException() {

        assertThrows(InvalidOrdersException.class, () -> {
            ordersController.add(null, null);
        });
    }

    @Test
    void updateOrders() throws InvalidOrdersException, OrdersNotFoundException {

        given(ordersService.existsById(uuid)).willReturn(true);

        ResponseEntity<String> response = ordersController.update(orders, uuid);

        then(ordersService).should().update(captorOrders.capture());

        assertThat(captorOrders.getValue()).isEqualTo(orders);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateOrdersThrowsOrdersNotFoundException() {

        given(ordersService.existsById(uuid)).willReturn(false);

        assertThrows(OrdersNotFoundException.class, () -> {
           ordersController.update(orders, uuid);
        });
    }

    @Test
    void updateOrdersThrowsInvalidOrdersException() {

        assertThrows(InvalidOrdersException.class, () -> {
           ordersController.update(null, null);
        });
    }

    @Test
    void listOrders() throws CustomerNotFoundException {

        Set<Orders> ordersList = new HashSet<>();

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);
        given(ordersService.findAll(customer)).willReturn(ordersList);

        ResponseEntity<Set<Orders>> response = ordersController.list(uuid);

        then(ordersService).should().findAll(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    @Test
    void listOrdersThrowsCustomerNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            ordersController.list(uuid);
        });
    }

    @Test
    void get() throws InvalidNameException, InvalidOrdersException, CustomerNotFoundException, OrdersNotFoundException {

        given(ordersService.existsById(uuid)).willReturn(true);

        ResponseEntity<Orders> response = ordersController.get(uuid);

        then(ordersService).should().getById(captorUUID.capture());

        assertThat(captorUUID.getValue()).isEqualTo(uuid);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
