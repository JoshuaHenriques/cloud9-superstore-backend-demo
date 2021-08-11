package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.InvalidCustomerException;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.jayhenri.cloud9.service.customer.OrdersService;
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

    private final CustomerService customerService;
    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService, CustomerService customerService) {
        this.ordersService = ordersService;
        this.customerService = customerService;
    }
    /**
     * Register response entity.
     *
     * @return the response entity
     */
    @PostMapping(value = "/add/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addOrder(@PathVariable UUID customerId, @RequestBody Orders orders)
            {

        ordersService.addOrder(customerService.getById(customerId), orders);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OrdersController", "addOrder");
        return new ResponseEntity<>("Successfully Created Customer's Order", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update customer.
     *
     * @return the response entity
     */
    @PutMapping(value = "/update/{ordersId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrder(@PathVariable("ordersId") UUID ordersId, @RequestBody Orders orders)
            {
        if (!ObjectUtils.isEmpty(orders)) {
            if (ordersService.existsById(ordersId)) {
                orders.setOrdersUUID(ordersId);
                ordersService.updateOrders(orders);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrderController", "updateOrder");
                return new ResponseEntity<>("Successfully Updated Order", responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidCustomerException();
    }

    /**
     * List customers response entity.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the response entity
     */
    @GetMapping(value = "/list/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Orders>> listOrders(@PathVariable UUID customerId) {
        // @RequestParam(defaultValue = "email") String sortBy
        Set<Orders> list = ordersService.findAllOrders(customerService.getById(customerId)); // sortBy

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("OrderController", "listCustomerOrders");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param customerId the email
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> getById(@PathVariable UUID orderId)
            throws InvalidNameException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(orderId)) {
            if (ordersService.existsById(orderId)) {
                Orders _orders = ordersService.getById(orderId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("OrdersController", "getById");
                return new ResponseEntity<>(_orders, responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidNameException();
    }
}
