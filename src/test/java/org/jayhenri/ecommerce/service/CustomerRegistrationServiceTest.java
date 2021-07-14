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

/**
 * The type Customer registration service test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {

    /**
     * The Test me.
     */
    CustomerRegistrationService testMe;

    /**
     * The Customer repository.
     */
    @Mock
    CustomerRepository customerRepository;

    /**
     * The Captor customer.
     */
    @Captor
    ArgumentCaptor<Customer> captorCustomer;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<String> captorString;

    /**
     * The Customer.
     */
    Customer customer;
    /**
     * The Order.
     */
    Order order;
    /**
     * The Cart.
     */
    Cart cart;
    /**
     * The Credit card.
     */
    CreditCard creditCard;
    /**
     * The Item.
     */
    Item item;

    /**
     * Sets up.
     */
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

    /**
     * Test add.
     *
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws CustomerAlreadyExistsException the customer already exists exception
     */
    @Test
    void testAdd() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Is valid postal code.
     */
    @Test
    void isValidPostalCode() {
        Boolean bool = testMe.isValidPostalCode("M1C8N3");

        assertThat(bool).isTrue();
    }

    /**
     * Is not valid postal code.
     */
    @Test
    void isNotValidPostalCode() {
        Boolean bool = testMe.isValidPostalCode("M1CM8N3");

        assertThat(bool).isFalse();
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        given(testMe.existsByEmail("testMe@gmail.com"))
                .willReturn(true);

        Boolean bool = testMe.existsByEmail("testMe@gmail.com");
        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("testMe@gmail.com");
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by email.
     */
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