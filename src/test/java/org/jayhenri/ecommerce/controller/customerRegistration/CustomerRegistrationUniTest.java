package org.jayhenri.ecommerce.controller.customerRegistration;

import org.jayhenri.ecommerce.controller.CustomerRegistrationController;
import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidItemException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.AddressService;
import org.jayhenri.ecommerce.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class CustomerRegistrationUniTest {

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Mock
    private CustomerService customerService;

    @Mock
    private AddressService addressService;

    private CustomerRegistrationController testMe;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        testMe = new CustomerRegistrationController(addressService, customerService);

        ArrayList<Item> items = new ArrayList<>();
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        ArrayList<CreditCard> creditCards = new ArrayList<>();

        Item item = new Item(
                "Test Product",
                "Test Description",
                9.99
        );

        items.add(item);

        OrderDetails orderDetails1 = new OrderDetails(
                "PROCESSING",
                "testMe@gmail.com",
                items,
                9.00
        );
        Cart cart = new Cart(
                items,
                "testMe@gmail.com",
                29.23
        );
        CreditCard creditCard = new CreditCard(
                "Ubuntu User",
                "4716902620158281",
                "8281",
                "05/25",
                "8281"
        );

        orderDetails.add(orderDetails1);
        creditCards.add(creditCard);

        customer = new Customer(
                "testMe",
                "TestMe",
                "2934811932",
                "testMe@gmail.com",
                "testMePassword",
                "082395",
                new Address(
                        "Test Me",
                        "29L",
                        "0L",
                        "New York",
                        "T2K9R3",
                        "Province"
                ),
                cart,
                creditCards,
                orderDetails
        );
    }

    @Test
    void register() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(false);
        given(customerService.existsByPhoneNumber(customer.getPhoneNumber())).willReturn(false);
        given(addressService.isValidPostalCode(customer.getAddress().getPostalCode())).willReturn(true);

        ResponseEntity<Customer> response = testMe.register(customer);

        then(customerService).should().add(captorCustomer.capture());

        assertThat(response).isEqualTo(ResponseEntity.ok().build());
        assertThat(customer).isEqualTo(captorCustomer.getValue());
    }

    @Test
    void registerThrowsInvalidPostalCodeException() {
        given(addressService.isValidPostalCode(customer.getAddress().getPostalCode())).willReturn(false);

        assertThrows(InvalidPostalCodeException.class, () -> {
            testMe.register(customer);
        });
    }

    @Test
    void registerThrowsCustomerAlreadyExistsException() {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(true);

        assertThrows(CustomerAlreadyExistsException.class, () -> {
            testMe.register(customer);
        });
    }
}
