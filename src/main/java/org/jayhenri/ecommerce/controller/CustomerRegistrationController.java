package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.service.AddressService;
import org.jayhenri.ecommerce.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/register")
public class CustomerRegistrationController {

    private final AddressService addressService;
    private final CustomerService customerService;

    @Autowired
    public CustomerRegistrationController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> register(@Valid @RequestBody Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException {
        if (customerService.existsByPhoneNumber(customer.getPhoneNumber()) || customerService.existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();
        else if (!addressService.isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customerService.add(customer);
        return ResponseEntity.ok().build();
    }
}
