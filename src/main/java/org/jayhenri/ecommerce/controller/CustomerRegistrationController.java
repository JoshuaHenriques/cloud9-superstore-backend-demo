package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidCustomerException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.service.AddressService;
import org.jayhenri.ecommerce.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Customer registration controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into the response body instead of rendering a template
@RequestMapping("api/register")
public class CustomerRegistrationController {

    private final AddressService addressService;
    private final CustomerService customerService;

    /**
     * Instantiates a new Customer registration controller.
     *
     * @param addressService  the address service
     * @param customerService the customer service
     */
    @Autowired
    public CustomerRegistrationController(AddressService addressService, CustomerService customerService) {
        this.addressService = addressService;
        this.customerService = customerService;
    }

    /**
     * Register response entity.
     *
     * @param customer the customer
     * @return the response entity
     * @throws CustomerAlreadyExistsException the customer already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws InvalidCustomerException       the invalid customer exception
     */
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@Valid @RequestBody Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException {
        if (ObjectUtils.isEmpty(customer)) throw new InvalidCustomerException();
        else if (customerService.existsByPhoneNumber(customer.getPhoneNumber()) || customerService.existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();
        else if (!addressService.isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customerService.add(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerRegistrationController", "register");
        return new ResponseEntity<String>("Successful", responseHeaders, HttpStatus.CREATED);
    }
}
