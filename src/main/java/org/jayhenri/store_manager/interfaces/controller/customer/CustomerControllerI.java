package org.jayhenri.store_manager.interfaces.controller.customer;

import org.jayhenri.store_manager.exception.alreadyexists.CustomerAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidCustomerException;
import org.jayhenri.store_manager.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.store_manager.exception.notfound.CustomerNotFoundException;
import org.jayhenri.store_manager.model.customer.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The interface Controller i.
 */
public interface CustomerControllerI {

    /**
     * Add.
     *
     * @param customer the customer
     * @return the response entity
     * @throws CustomerAlreadyExistsException the customer already exists exception
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws InvalidCustomerException       the invalid customer exception
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException;

    /**
     * Remove.
     *
     * @param customer   the customer
     * @param customerId the customer id
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Customer customer, @PathVariable UUID customerId) throws InvalidCustomerException, CustomerNotFoundException;

    /**
     * Update.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/delete/{customerId}")
    ResponseEntity<String> delete(@PathVariable UUID customerId) throws CustomerNotFoundException;

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Customer>> list();

    /**
     * Get response entity.
     *
     * @param customerId the customer id
     * @return the response entity
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/get/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> get(@PathVariable UUID customerId) throws InvalidNameException, CustomerNotFoundException;
}
