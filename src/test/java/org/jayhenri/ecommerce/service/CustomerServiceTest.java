package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Customer service test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    /**
     * The Test me.
     */
    CustomerService testMe;

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
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        testMe = new CustomerService(customerRepository);
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
     * Add.
     *
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws CustomerAlreadyExistsException the customer already exists exception
     */
    @Test
    void add() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        testMe.delete(this.customer);

        then(customerRepository).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        testMe.update(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Find all customers.
     */
// Do later
    @Test
    void findAllCustomers() {
    }

    /**
     * Find all credit cards.
     */
    @Test
    void findAllCreditCards() {
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
    }

    /**
     * Add to cart.
     */
    @Test
    void addToCart() {
    }

    /**
     * Remove from cart.
     */
    @Test
    void removeFromCart() {
    }

    /**
     * Empty cart.
     */
    @Test
    void emptyCart() {
    }

    /**
     * Gets cart.
     */
    @Test
    void getCart() {
    }

    /**
     * Add credit card.
     */
    @Test
    void addCreditCard() {
    }

    /**
     * Remove credit card.
     */
    @Test
    void removeCreditCard() {
    }

    /**
     * Add order.
     */
    @Test
    void addOrder() {
    }

    /**
     * Update order.
     */
    @Test
    void updateOrder() {
    }

    /**
     * Find all orders.
     */
    @Test
    void findAllOrders() {
    }
}