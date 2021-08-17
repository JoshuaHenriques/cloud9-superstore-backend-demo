package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.OrdersNotFoundException;
import org.jayhenri.cloud9.interfaces.service.ServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.jayhenri.cloud9.interfaces.service.customer.OrdersServiceI;
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
public class OrdersController {

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
    @PostMapping(value = "/add/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@PathVariable UUID customerId, @RequestBody Orders orders) throws InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orders)) {
            ordersService.add(customerService.getById(customerId), orders);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("OrdersController", "addOrder");
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
    public ResponseEntity<String> updateOrder(@PathVariable("ordersId") UUID ordersId, @RequestBody Orders orders) throws OrdersNotFoundException, InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orders)) {
            if (ordersService.existsById(ordersId)) {
                orders.setOrdersUUID(ordersId);
                ordersService.update(orders);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrderController", "updateOrder");
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
    public ResponseEntity<Set<Orders>> listOrders(@PathVariable UUID customerId) throws CustomerNotFoundException {
        // @RequestParam(defaultValue = "email") String sortBy
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
    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getById(@PathVariable UUID orderId)
            throws InvalidNameException, CustomerNotFoundException, OrdersNotFoundException, InvalidOrdersException {
        if (!ObjectUtils.isEmpty(orderId)) {
            if (ordersService.existsById(orderId)) {
                Orders _orders = ordersService.getById(orderId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrdersController", "getById");
                return new ResponseEntity<>(_orders, responseHeaders, HttpStatus.OK);
            } else
                throw new OrdersNotFoundException();
        } else
            throw new InvalidOrdersException();
    }
}
