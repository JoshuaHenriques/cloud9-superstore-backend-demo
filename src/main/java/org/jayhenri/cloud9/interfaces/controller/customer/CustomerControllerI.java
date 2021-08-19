package org.jayhenri.cloud9.interfaces.controller.customer;

import org.jayhenri.cloud9.exception.alreadyexists.CustomerAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.model.customer.Customer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The interface Controller i.
 *
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
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> add(@RequestBody Customer customer) throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException;

    /**
     * Remove.
     *
     * @param customer the customer
     * @param uuid     the uuid
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update/{uuid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> update(@RequestBody Customer customer, @PathVariable("uuid") UUID uuid) throws InvalidCustomerException, CustomerNotFoundException;

    /**
     * Update.
     *
     * @param uuid the uuid
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/delete/{uuid}")
    ResponseEntity<String> delete(@PathVariable UUID uuid) throws CustomerNotFoundException;

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
     * @param uuid the uuid
     * @return the response entity
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Customer> get(@PathVariable UUID uuid) throws InvalidNameException, CustomerNotFoundException;
}
