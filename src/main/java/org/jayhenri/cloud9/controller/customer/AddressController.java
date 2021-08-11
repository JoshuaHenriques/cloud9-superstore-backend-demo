package org.jayhenri.cloud9.controller.customer;

import org.jayhenri.cloud9.exception.invalid.InvalidAddressException;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * The type Address controller.
 */
@RestController
@RequestMapping("api/customer/address")
public class AddressController {

    private final CustomerService customerService;

    private Customer customer;

    /**
     * Instantiates a new Address controller.
     *
     * @param customerService the customer service
     */
    @Autowired
    public AddressController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Update customer.
     *
     * @param uuid    the uuid
     * @param address the customer
     * @return the response entity
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @PutMapping(value = "/update/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateAddress(@PathVariable("customerId") UUID uuid, @RequestBody Address address)
            throws InvalidCustomerException, CustomerNotFoundException, InvalidAddressException {
        if (!ObjectUtils.isEmpty(address)) {
            if (customerService.existsById(uuid)) {
                customer.setCustomerUUID(uuid);
                customer.setAddress(address);
                customerService.update(customer);

                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("AddressController", "updateAddress");
                return new ResponseEntity<>("Successfully Updated Customer's Address", responseHeaders, HttpStatus.OK);
            } else
                throw new CustomerNotFoundException();
        } else
            throw new InvalidAddressException();
    }
}
