package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.CreditCard;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.OrderDetails;
import org.jayhenri.ecommerce.service.AddressService;
import org.jayhenri.ecommerce.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * The type Customer registration controller test.
 */
@WebMvcTest(CustomerRegistrationController.class)
@ExtendWith(MockitoExtension.class)
class CustomerRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @Mock
    private CustomerService customerService;

    private CustomerRegistrationController testMe;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        testMe = new CustomerRegistrationController(addressService, customerService);
    }

    // https://spring.io/guides/gs/testing-web/
    /**
     * Register.
     */
//    @Test
//    void register() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
//        given(testMe.register(customer)).willReturn(ResponseEntity.ok().build());
//
//        mockMvc.perform(get("/api/customers/register/customer"))
//                .andExpect()
//    }
}