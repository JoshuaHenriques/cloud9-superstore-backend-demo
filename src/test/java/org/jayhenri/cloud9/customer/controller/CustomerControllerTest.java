package org.jayhenri.cloud9.customer.controller;

import org.jayhenri.cloud9.controller.customer.CustomerController;
import org.jayhenri.cloud9.exception.alreadyexists.CustomerAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidCustomerException;
import org.jayhenri.cloud9.exception.invalid.InvalidPostalCodeException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CustomerControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.AddressServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Customer controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Mock
    private CustomerServiceI customerService;

    @Mock
    private AddressServiceI addressService;

    private CustomerControllerI customerController;

    private Customer customer;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        customerController = new CustomerController(customerService, addressService);

        customer = new Customer(
                "customer.email@gmail.com",
                new Login(),
                new Cart(),
                new Address(
                        "Paris St",
                        "2774",
                        "000",
                        "Sudbury",
                        "P3E 5B2",
                        "Ontario"
                ),
                new HashSet<CreditCard>(),
                new HashSet<Orders>(),
                "Stew",
                "Griffin",
                "6478499939",
                "01/01/1998"
        );
    }

    /**
     * Register.
     *
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws CustomerAlreadyExistsException the customer already exists exception
     * @throws InvalidCustomerException       the invalid customer exception
     */
    @Test
    void addCustomer() throws InvalidPostalCodeException, CustomerAlreadyExistsException, InvalidCustomerException {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(false);
        given(customerService.existsById(customer.getCustomerUUID())).willReturn(false);
        given(customerService.existsByPhoneNumber(customer.getPhoneNumber())).willReturn(false);
        given(addressService.isValidPostalCode(customer.getAddress().getPostalCode())).willReturn(true);

        ResponseEntity<String> response = customerController.add(customer);

        then(customerService).should().add(captorCustomer.capture());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(customer).isEqualTo(captorCustomer.getValue());
    }

    /**
     * Register throws invalid postal code exception.
     */
    @Test
    void addCustomerThrowsInvalidPostalCodeException() {
        given(addressService.isValidPostalCode(customer.getAddress().getPostalCode())).willReturn(false);

        assertThrows(InvalidPostalCodeException.class, () -> {
            customerController.add(customer);
        });
    }

    /**
     * Register throws customer already exists exception.
     */
    @Test
    void addCustomerThrowsCustomerAlreadyExistsException() {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(true);

        assertThrows(CustomerAlreadyExistsException.class, () -> {
            customerController.add(customer);
        });
    }

    /**
     * Update customer.
     *
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void updateCustomer() throws InvalidCustomerException, CustomerNotFoundException {
        given(customerService.existsById(customer.getCustomerUUID())).willReturn(true);

        ResponseEntity<String> response = customerController.update(customer, customer.getCustomerUUID());

        then(customerService).should().update(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Update customer throws invalid customer exception.
     */
    @Test
    void updateCustomerThrowsInvalidCustomerException() {
        assertThrows(InvalidCustomerException.class, () -> {
            customerController.update(null, null);
        });
    }

    /**
     * Update customer throws customer already exists exception.
     */
    @Test
    void updateCustomerThrowsCustomerNotFoundException() {
        given(customerService.existsById(customer.getCustomerUUID())).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerController.update(customer, customer.getCustomerUUID());
        });
    }

    /**
     * Delete customer.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void deleteCustomer() throws CustomerNotFoundException {
        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);

        ResponseEntity<String> response = customerController.delete(uuid);

        then(customerService).should().remove(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Delete customer throws customer not found exception.
     */
    @Test
    void deleteCustomerThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            customerController.delete(UUID.randomUUID());
        });
    }

    /**
     * List customers.
     */
    @Test
    @Disabled
    void listCustomers() {

    }

//    /**
//     * Gets by email.
//     *
//     * @throws InvalidNameException      the invalid name exception
//     * @throws CustomerNotFoundException the customer not found exception
//     */
//    @Test
//    void getByEmail() throws InvalidNameException, CustomerNotFoundException {
//        given(customerService.existsByEmail("customer.email@gmail.com")).willReturn(true);
//
//        ResponseEntity<Customer> response = customerController.getByEmail("customer.email@gmail.com");
//
//        then(customerService).should().getByEmail(captorString.capture());
//
//        assertThat("customerController@gmail.com").isEqualTo(captorString.getValue());
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//
//    /**
//     * Gets by email throws invalid name exception.
//     */
//    @Test
//    void getByEmailThrowsInvalidNameException() {
//        assertThrows(InvalidNameException.class, () -> {
//            customerController.getByEmail(null);
//        });
//    }
//
//    /**
//     * Gets by email throws customer not found exception.
//     */
//    @Test
//    void getByEmailThrowsCustomerNotFoundException() {
//        given(customerService.existsByEmail("customerController@gmail.com")).willReturn(false);
//
//        assertThrows(CustomerNotFoundException.class, () -> {
//            customerController.getByEmail("customerController@gmail.com");
//        });
//    }
}
