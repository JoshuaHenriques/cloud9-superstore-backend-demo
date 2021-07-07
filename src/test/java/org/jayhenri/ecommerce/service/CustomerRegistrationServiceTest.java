package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {

    @InjectMocks
    CustomerRegistrationService customerRegistrationService;

    @Mock
    CustomerRepository customerRepository;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<String> captorString;



    // Given
    Customer customer;
    Order order;
    Cart cart;
    CreditCard creditCard;
    Item item;

    @BeforeEach
    void setUp() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<CreditCard> creditCards = new ArrayList<>();

        item = new Item(
                "Test Product",
                "Test Description",
                9.99
        );

        items.add(item);

        order = new Order(
              "PROCESSING",
              "testMe@gmail.com",
                items,
                9.00
        );
        cart = new Cart(
                items,
                "testMe@gmail.com",
                29.23
        );
        creditCard = new CreditCard(
                "Ubuntu User",
                "4716902620158281",
                "8281",
                "05/25",
                "8281"
        );

        orders.add(order);
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
                orders
        );

    }
    // ask reddit
    @Test
    @Disabled
    void testAdd() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        // When
        customerRegistrationService.add(this.customer);
        // Then
        then(customerRepository).should().save(captorCustomer.capture());
        Customer _customer = captorCustomer.getValue();
        assertThat(_customer).isEqualTo(this.customer);
    }

    @Test
    @Disabled
    void testAddThrowsInvalidPostalCodeException() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        CustomerRegistrationService testMe = mock(CustomerRegistrationService.class);
        given(customerRegistrationService.existsByEmail("testMe@gmail.com")).willReturn(true);
        doThrow(new CustomerAlreadyExistsException())
                .when(testMe)
                .add(this.customer);

        testMe.add(this.customer);
    }

    @Test
    @Disabled
    void testAddThrowsCustomerAlreadyExistsException() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        // When
        customerRegistrationService.add(this.customer);
        // Then
        then(customerRepository).should().save(captorCustomer.capture());
        Customer _customer = captorCustomer.getValue();
        assertThat(_customer).isEqualTo(this.customer);
    }

    @Test
    @Disabled
    void existsByPhoneNumber() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        // Given
        // When
        when(customerRegistrationService.existsByPhoneNumber("2934811932")).thenReturn(true);
        boolean exists = customerRegistrationService.existsByPhoneNumber("2934811932");
        // Then
        verify(customerRepository).existsPhoneNumber("2934811932");
        assertThat(exists).isTrue();
    }
    @Test
    @Disabled
    void isValidPostalCode() {
    }

    @Test
    @Disabled
    void existsByEmail() {
    }
}