package org.jayhenri.cloud9.controller.customer;

import java.util.List;
import java.util.UUID;

import javax.naming.InvalidNameException;

import org.jayhenri.cloud9.exception.alreadyexists.CustomerAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CustomerControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.AddressServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Customer controller.
 */
@RestController
@RequestMapping("api/customer")
public class CustomerController implements CustomerControllerI {

    private final CustomerServiceI customerService;
    private final AddressServiceI addressService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService the customer service
     * @param addressService  the inventory service
     */
    @Autowired
    public CustomerController(CustomerServiceI customerService, AddressServiceI addressService) {
        this.customerService = customerService;
        this.addressService = addressService;
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
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> add(@RequestBody Customer customer)
            throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException {

        if (ObjectUtils.isEmpty(customer))
            throw new InvalidCustomerException();

        else if (customerService.existsByPhoneNumber(customer.getPhoneNumber())
                || customerService.existsByEmail(customer.getEmail())
                || customerService.existsById(customer.getCustomerUUID()))
            throw new CustomerAlreadyExistsException();

        else if (!addressService.isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customerService.add(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerRegistrationController", "add");
        return new ResponseEntity<>("Successfully Created Customer", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update customer.
     *
     * @param customerId the customerId
     * @param customer   the customer
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody Customer customer, @PathVariable UUID customerId)
            throws InvalidCustomerException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customer)) {
            if (customerService.existsById(customer.getCustomerUUID())) {
                customer.setCustomerUUID(customerId);
                customerService.update(customer);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "update");
                return new ResponseEntity<>("Successfully Updated Customer", responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidCustomerException();
    }

    /**
     * Delete customer.
     *
     * @param customerId the email
     * @return the response entity
     * @throws CustomerNotFoundException the customer not found exception
     */
    @DeleteMapping(value = "/delete/{customerId}")
    public ResponseEntity<String> delete(@PathVariable UUID customerId)
            throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            Customer _customer = customerService.getById(customerId);
            customerService.remove(_customer);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "delete");
            return new ResponseEntity<>("Successfully Deleted Customer", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * List customers response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> list() {

        List<Customer> list = customerService.findAll();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerController", "list");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param customerId the email
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/get/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> get(@PathVariable UUID customerId)
            throws InvalidNameException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customerId)) {
            if (customerService.existsById(customerId)) {
                Customer _customer = customerService.getById(customerId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "get");
                return new ResponseEntity<>(_customer, responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidNameException();
    }

//    /**
//     * Gets by email.
//     *
//     * @param email the email
//     * @return the by email
//     * @throws InvalidNameException      the invalid name exception
//     * @throws CustomerNotFoundException the customer not found exception
//     */
//    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Customer> getByEmail(@PathVariable String email)
//            throws InvalidNameException, CustomerNotFoundException {
//        if (!ObjectUtils.isEmpty(email)) {
//            if (customerService.existsByEmail(email)) {
//                Customer _customer = customerService.getByEmail(email);
//
//                HttpHeaders responseHeaders = new HttpHeaders();
//                responseHeaders.set("CustomerController", "getByEmail");
//                return new ResponseEntity<>(_customer, responseHeaders, HttpStatus.OK);
//            } else
//                throw new CustomerNotFoundException();
//        } else
//            throw new InvalidNameException();
//    }
}
