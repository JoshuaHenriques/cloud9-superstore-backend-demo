package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.CustomerNotFoundException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Cart;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.service.CustomerService;
import org.jayhenri.ecommerce.service.OrderDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InvalidNameException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/orderDB")
public class OrderDBController {

    @Autowired
    private OrderDBService orderDBService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void save(@Valid @RequestBody Cart cart) throws InvalidNameException, CustomerNotFoundException {
        orderDBService.addOrderToDB(cart);
    }
}
