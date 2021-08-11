package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.alreadyexists.CustomerAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.service.customer.AddressService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;
import java.util.UUID;

/**
 * The type Customer controller.
 */
@RestController // Indicates that the data returned by each method will be written straight into
// the response body instead of rendering a template
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final AddressService addressService;

    /**
     * Instantiates a new Customer controller.
     *
     * @param customerService the customer service
     * @param addressService  the inventory service
     */
    @Autowired
    public CustomerController(CustomerService customerService, AddressService addressService) {
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
    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Customer customer)
            throws CustomerAlreadyExistsException, InvalidPostalCodeException, InvalidCustomerException {

        if (ObjectUtils.isEmpty(customer))
            throw new InvalidCustomerException();

        else if (customerService.existsByPhoneNumber(customer.getPhoneNumber())
                || customerService.existsByEmail(customer.getEmail()))
            throw new CustomerAlreadyExistsException();

        else if (!addressService.isValidPostalCode(customer.getAddress().getPostalCode()))
            throw new InvalidPostalCodeException();

        customerService.add(customer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerRegistrationController", "register");
        return new ResponseEntity<>("Successfully Created Customer", responseHeaders, HttpStatus.CREATED);
    }

    /**
     * Update customer.
     *
     * @param uuid     the uuid
     * @param customer the customer
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") UUID uuid, @RequestBody Customer customer)
            throws InvalidCustomerException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customer)) {
            if (customerService.existsById(customer.getCustomerUUID())) {
                customer.setCustomerUUID(uuid);
                customerService.update(customer);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "updateCustomer");
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
    public ResponseEntity<String> deleteCustomer(@PathVariable UUID customerId)
            throws CustomerNotFoundException {
        if (customerService.existsById(customerId)) {
            Customer _customer = customerService.getById(customerId);
            customerService.delete(_customer);

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("CustomerController", "deleteCustomer");
            return new ResponseEntity<>("Successfully Deleted Customer", responseHeaders, HttpStatus.OK);
        } else
            throw new CustomerNotFoundException();
    }

    /**
     * List customers response entity.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the response entity
     */
    @GetMapping(value = "/list/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> listCustomers(@RequestParam(defaultValue = "0") Integer pageNo,
                                                        @RequestParam(defaultValue = "50") Integer pageSize) {
        // @RequestParam(defaultValue = "email") String sortBy
        List<Customer> list = customerService.findAllCustomers(pageNo, pageSize); // sortBy

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("CustomerController", "listCustomers");
        return new ResponseEntity<>(list, responseHeaders, HttpStatus.OK);
    }

    /**
     * Gets by email.
     *
     * @param email the email
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getByEmail(@PathVariable String email)
            throws InvalidNameException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(email)) {
            if (customerService.existsByEmail(email)) {
                Customer _customer = customerService.getByEmail(email);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "getByEmail");
                return new ResponseEntity<>(_customer, responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidNameException();
    }

    /**
     * Gets by email.
     *
     * @param customerId the email
     * @return the by email
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getById(@PathVariable UUID customerId)
            throws InvalidNameException, CustomerNotFoundException {
        if (!ObjectUtils.isEmpty(customerId)) {
            if (customerService.existsById(customerId)) {
                Customer _customer = customerService.getById(customerId);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("CustomerController", "getById");
                return new ResponseEntity<>(_customer, responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidNameException();
    }
}
