package org.jayhenri.cloud9.customer.service;

import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.repository.customer.CustomerRepository;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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
    private CustomerServiceI customerService;

    /**
     * The Customer repository.
     */
    @Mock
    private CustomerRepository customerRepository;

    /**
     * The Captor customer.
     */
    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<String> captorString;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    /**
     * The Customer.
     */
    private Customer customer;

    /**
     * The Customer.
     */
    private UUID uuid;

    /**
     * The Customer.
     */
    private Cart cart;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        customer = new Customer();

        customerService = new CustomerService(customerRepository);

        uuid = UUID.randomUUID();
    }

    /**
     * Test add.
     */
    @Test
    void testAdd() {
        customerService.add(customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(customerRepository.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = customerService.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(customerRepository.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = customerService.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Add.
     */
    @Test
    void add() {
        customerService.add(customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        customerService.remove(customer);

        then(customerRepository).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        customerService.update(customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Find all customers.
     * Do later.
     */
    @Test
    @Disabled
    void findAllCustomers() {
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        String email = "customerService@gmail.com";
        given(customerRepository.existsByEmail(email)).willReturn(true);

        boolean bool = customerService.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isTrue();
    }

    /**
     * Exists by email.
     */
    @Test
    void doesNotExistsByEmail() {
        String email = "customerService@gmail.com";
        given(customerRepository.existsByEmail(email)).willReturn(false);

        boolean bool = customerService.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isFalse();
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
        String email = "customerService@gmail.com";
        given(customerRepository.getByEmail(email)).willReturn(customer);
        Customer _customer = customerService.getByEmail(email);

        then(customerRepository).should().getByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(customer).isEqualTo(_customer);
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(customerRepository.existsById(uuid)).willReturn(true);

        boolean exists = customerService.existsById(uuid);

        then(customerRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(customerRepository.existsById(uuid)).willReturn(false);

        boolean exists = customerService.existsById(uuid);

        then(customerRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(customerRepository.getById(uuid)).willReturn(customer);

        Customer _customer = customerService.getById(uuid);

        then(customerRepository).should().getById(captorUUID.capture());

        assertThat(_customer).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }
}