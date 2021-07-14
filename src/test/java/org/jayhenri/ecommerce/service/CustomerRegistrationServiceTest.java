package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {

    CustomerRegistrationService testMe;

    @Mock
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Customer> captorCustomer;

    @Captor
    ArgumentCaptor<String> captorString;

    Customer customer;
    Order order;
    Cart cart;
    CreditCard creditCard;
    Item item;

    @BeforeEach
    void setUp() {
        testMe = new CustomerRegistrationService(customerRepository);
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

    @Test
    void testAdd() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    @Test
    void testAddThrowsInvalidPostalCodeException() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        CustomerRegistrationService mockMeToTest = mock(CustomerRegistrationService.class);

        willThrow(InvalidPostalCodeException.class).given(mockMeToTest).add(this.customer);

        assertThatThrownBy(() -> {
            mockMeToTest.add(this.customer);
        }).isInstanceOf(InvalidPostalCodeException.class);
    }

    @Test
    void testAddThrowsCustomerAlreadyExistsException() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        CustomerRegistrationService mockMeToTest = mock(CustomerRegistrationService.class);

        willThrow(CustomerAlreadyExistsException.class).given(mockMeToTest).add(this.customer);

        assertThatThrownBy(() -> {
            mockMeToTest.add(this.customer);
        }).isInstanceOf(InvalidPostalCodeException.class);
    }

    @Test
    void existsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    @Test
    void doesNotExistsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    @Test
    void isValidPostalCode() {
        Boolean bool = testMe.isValidPostalCode("M1C8N3");

        assertThat(bool).isTrue();
    }

    @Test
    void isNotValidPostalCode() {
        Boolean bool = testMe.isValidPostalCode("M1CM8N3");

        assertThat(bool).isFalse();
    }

    @Test
    void existsByEmail() {
        given(testMe.existsByEmail("testMe@gmail.com"))
                .willReturn(true);

        Boolean bool = testMe.existsByEmail("testMe@gmail.com");
        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("testMe@gmail.com");
        assertThat(bool).isTrue();
    }

    @Test
    void doesNotExistsByEmail() {
        given(testMe.existsByEmail("testMe@gmail.com"))
                .willReturn(false);

        Boolean bool = testMe.existsByEmail("testMe@gmail.com");
        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("testMe@gmail.com");
        assertThat(bool).isFalse();
    }
}