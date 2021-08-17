package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.alreadyexists.CreditCardAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CreditCardNotFoundException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.interfaces.service.customer.CreditCardServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.service.customer.CreditCardService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

/**
 * The type Credit card controller.
 */
public class CreditCardController {

    private final CustomerServiceI customerService;
    private final CreditCardServiceI creditCardService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService   the customer service
     * @param creditCardService the credit card service
     */
    @Autowired
    public CreditCardController(CustomerServiceI customerService, CreditCardServiceI creditCardService) {
        this.customerService = customerService;
        this.creditCardService = creditCardService;
    }

    /**
     * Add credit card.
     *
     * @param customerId the customer id
     * @param creditCard the credit card
     * @return the response entity
     * @throws CustomerNotFoundException        the customer not found exception
     * @throws InvalidOrdersException           the invalid orders exception
     * @throws CreditCardAlreadyExistsException the credit card already exists exception
     */
    @PostMapping(value = "/{customerId}/creditCard/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addCreditCard(@PathVariable UUID customerId, @RequestBody CreditCard creditCard)
            throws CustomerNotFoundException, InvalidOrdersException, CreditCardAlreadyExistsException {
        if (!ObjectUtils.isEmpty(creditCard)) {

            if (customerService.existsById(customerId)) {
                if (customerService.existsByCCN(creditCard.getCcn())) {
                    creditCardService.add(customerService.getById(customerId), creditCard);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.set("CustomerController", "addCreditCard");
                    return new ResponseEntity<>("Successfully Added Credit Card", responseHeaders, HttpStatus.CREATED);
                } else
                    throw new CreditCardAlreadyExistsException();
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidOrdersException();
    }

    /**
     * Remove credit card.
     *
     * @param customerId the customer id
     * @param cardId     the card id
     * @return the response entity
     * @throws CustomerNotFoundException   the customer not found exception
     * @throws CreditCardNotFoundException the credit card not found exception
     */
    @DeleteMapping(value = "/{customerId}/creditCard/remove/{cardId}")
    public ResponseEntity<String> removeCreditCard(@PathVariable UUID customerId, @PathVariable UUID cardId)
            throws CustomerNotFoundException, CreditCardNotFoundException {
        if (customerService.existsById(customerId)) {
            if (creditCardService.existsById(customerService.getById(customerId), cardId)) {
                creditCardService.remove(customerService.getById(customerId), cardId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "removeCreditCard");
                return new ResponseEntity<>("Successfully Removed Credit Card", responseHeaders, HttpStatus.OK);
            } else
                throw new CreditCardNotFoundException();
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
            Set<CreditCard> list = creditCardService.findAll(customerService.getById(customerId));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "listCreditCards");
            return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }
}
