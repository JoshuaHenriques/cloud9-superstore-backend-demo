package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Login;
import org.jayhenri.ecommerce.service.CustomerRegistrationService;
import org.jayhenri.ecommerce.service.LoginRegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/register")
@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
public class CustomerRegistrationController {

    private UUID uuid;

    @Autowired
    private CustomerRegistrationService customerRegistrationService;

    @Autowired
    private LoginRegistrationService loginRegistrationService;

    @GetMapping(value = "/test")
    public ResponseEntity test() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> register(@Valid @RequestBody Login login) throws CustomerAlreadyExistsException {
        this.uuid = UUID.randomUUID();
        loginRegistrationService.saveLogin(login, this.uuid);
        // Logger
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/customerDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> customerDetails(@Valid @RequestBody Customer customer) throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        customerRegistrationService.saveCustomer(customer, this.uuid);
        return ResponseEntity.ok().build();
    }
}
