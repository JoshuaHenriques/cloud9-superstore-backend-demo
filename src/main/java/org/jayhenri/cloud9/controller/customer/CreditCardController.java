package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.CustomerNotFoundException;
import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.service.customer.AddressService;
import org.jayhenri.cloud9.service.customer.CreditCardService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * The type Credit card controller.
 */
public class CreditCardController {

    private final CustomerService customerService;
    private final CreditCardService creditCardService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService   the customer service
     * @param creditCardService the credit card service
     */
    @Autowired
    public CreditCardController(CustomerService customerService, CreditCardService creditCardService) {
        this.customerService = customerService;
        this.creditCardService = creditCardService;
    }

    /**
     * Add credit card.
     *
     * @param customerId the customer id
     * @param creditCard the credit card
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PostMapping(value = "/{customerId}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCreditCard(@PathVariable UUID customerId, @RequestBody CreditCard creditCard)
            throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            // Validate CreditCard
            creditCardService.addCreditCard(customerService.getById(customerId), creditCard);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "addCreditCard");
            return new ResponseEntity<>("Successfully Added Credit Card", responseHeaders, HttpStatus.CREATED);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * Remove credit card.
     *
     * @param customerId the customer id
     * @param cardId     the card id
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/{customerId}/creditCard/remove/{cardId}")
    public ResponseEntity<String> removeCreditCard(@PathVariable UUID customerId, @PathVariable UUID cardId)
            throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            creditCardService.removeCreditCard(customerService.getById(customerId), cardId);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "removeCreditCard");
            return new ResponseEntity<>("Successfully Removed Credit Card", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * List credit cards list.
     *
     * @param customerId the email
     * @return the list
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{customerId}/creditCards/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CreditCard>> listCreditCards(@PathVariable UUID customerId)
            throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            Set<CreditCard> list = creditCardService.findAllCreditCards(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "listCreditCards");
            return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }
}
