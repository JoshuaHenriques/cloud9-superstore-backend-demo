package org.jayhenri.store_manager.controller.customer;

import org.jayhenri.store_manager.exception.invalid.InvalidOrdersException;
import org.jayhenri.store_manager.exception.notfound.CustomerNotFoundException;
import org.jayhenri.store_manager.exception.notfound.OrdersNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.customer.OrdersControllerI;
import org.jayhenri.store_manager.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.OrdersServiceI;
import org.jayhenri.store_manager.model.customer.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.Set;
import java.util.UUID;

/**
 * The type Orders controller.
 */
@RestController
@RequestMapping("api/orders")
public class OrdersController implements OrdersControllerI {

    private final CustomerServiceI customerService;
    private final OrdersServiceI ordersService;

    /**
     * Instantiates a new Orders controller.
     *
     * @param ordersService   the orders service
     * @param customerService the customer service
     */
    @Autowired
    public OrdersController(OrdersServiceI ordersService, CustomerServiceI customerService) {
        this.ordersService = ordersService;
        this.customerService = customerService;
    }

    /**
     * Register response entity.
     *
     * @param customerId the customer id
     * @param orders     the orders
     * @return the response entity
     * @throws InvalidOrdersException the invalid orders exception
     */
    @PostMapping(value = "/add/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Orders orders, @PathVariable UUID customerId) throws InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orders)) {
            ordersService.add(customerService.getById(customerId), orders);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OrdersController", "add");
            return new ResponseEntity<>("Successfully Created Customer's Order", responseHeaders, HttpStatus.CREATED);
        } else
            throw new InvalidOrdersException();
    }

    /**
     * Update customer.
     *
     * @param ordersId the orders id
     * @param orders   the orders
     * @return the response entity`
     * @throws OrdersNotFoundException the orders not found exception
     * @throws InvalidOrdersException  the invalid orders exception
     */
    @PutMapping(value = "/update/{ordersId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Orders orders, @PathVariable UUID ordersId) throws OrdersNotFoundException, InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orders)) {
            if (ordersService.existsById(ordersId)) {
                orders.setOrdersUUID(ordersId);
                ordersService.update(orders);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrderController", "update");
                return new ResponseEntity<>("Successfully Updated Order", responseHeaders, HttpStatus.OK);
            } else
                throw new OrdersNotFoundException();
        } else
            throw new InvalidOrdersException();
    }

    /**
     * List customers response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/list/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Orders>> list(@PathVariable UUID customerId) throws CustomerNotFoundException {

        if (customerService.existsById(customerId)) {
            Set<Orders> list = ordersService.findAll(customerService.getById(customerId)); // sortBy

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OrderController", "listCustomerOrders");
            return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Gets by email.
     *
     * @param orderId the order id
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     * @throws OrdersNotFoundException   the orders not found exception
     * @throws InvalidOrdersException    the invalid orders exception
     */
    @GetMapping(value = "/get/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> get(@PathVariable UUID orderId)
            throws InvalidNameException, CustomerNotFoundException, OrdersNotFoundException, InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orderId)) {
            if (ordersService.existsById(orderId)) {
                Orders _orders = ordersService.getById(orderId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrdersController", "get");
                return new ResponseEntity<>(_orders, responseHeaders, HttpStatus.OK);
            } else
                throw new OrdersNotFoundException();
        } else
            throw new InvalidOrdersException();
    }
}
