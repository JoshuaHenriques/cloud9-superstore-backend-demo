package org.jayhenri.cloud9.interfaces.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.exception.notfound.OrdersNotFoundException;
import org.jayhenri.cloud9.model.customer.Orders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.Set;
import java.util.UUID;

@RequestMapping("api/orders")
public interface OrdersControllerI {

    @PostMapping(value = "/add/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Orders orders, @PathVariable UUID customerId) throws InvalidOrdersException;

    @PutMapping(value = "/update/{ordersId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Orders orders, @PathVariable("ordersId") UUID ordersId) throws OrdersNotFoundException, InvalidOrdersException;

    @GetMapping(value = "/list/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Orders>> list(@PathVariable UUID customerId) throws CustomerNotFoundException;

    @GetMapping(value = "/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> get(@PathVariable UUID orderId)
            throws InvalidNameException, CustomerNotFoundException, OrdersNotFoundException, InvalidOrdersException;
}
