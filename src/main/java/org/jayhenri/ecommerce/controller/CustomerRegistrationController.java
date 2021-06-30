package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Login;
import org.jayhenri.ecommerce.service.CustomerRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/register")
@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
public class CustomerRegistrationController {

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> register(@Valid @RequestBody Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {
        customerRegistrationService.saveCustomer(customer);
        // Logger
        return ResponseEntity.ok().build();
    }
}
