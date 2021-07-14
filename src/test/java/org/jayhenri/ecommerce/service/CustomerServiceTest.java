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

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    CustomerService testMe;

    @Mock
    CustomerRepository customerRepository;

    @Captor
    ArgumentCaptor<Customer> captorCustomer;

    @Captor
    ArgumentCaptor<String> captorString;

    Customer customer;

    @BeforeEach
    void setUp() {
        testMe = new CustomerService(customerRepository);
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
    void add() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    @Test
    void delete() {
        testMe.delete(this.customer);

        then(customerRepository).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    @Test
    void update() {
        testMe.update(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    // Do later
    @Test
    void findAllCustomers() {
    }

    @Test
    void findAllCreditCards() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void getByEmail() {
    }

    @Test
    void addToCart() {
    }

    @Test
    void removeFromCart() {
    }

    @Test
    void emptyCart() {
    }

    @Test
    void getCart() {
    }

    @Test
    void addCreditCard() {
    }

    @Test
    void removeCreditCard() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void findAllOrders() {
    }
}