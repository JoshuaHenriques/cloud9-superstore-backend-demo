package org.jayhenri.ecommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jayhenri.ecommerce.model.Address;
import org.jayhenri.ecommerce.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * The type Customer repository data jpa test.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository testMe;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        customer = new Customer("testMe", "TestMe", "2934811932", "testMe@gmail.com", "testMePassword", "082395",
                new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
    }

    /**
     * Database should be empty.
     */
    @Test
    void emptyDatabase() {
        List<Customer> customer = testMe.findAll();
        assertThat(customer).isEmpty();
    }

    /**
     * Database should store customer.
     */
    @Test
    void storeCustomer() {
        Customer _customer = testMe.save(customer);

        assertThat(_customer).hasFieldOrPropertyWithValue("email", "testMe@gmail.com");
        assertThat(_customer).hasFieldOrPropertyWithValue("phoneNumber", "2934811932");
        assertThat(_customer).hasFieldOrProperty("address");
    }

    @Test
    void findAllInventory() {
        Customer customer0 = new Customer("testMe", "TestMe", "2934811932", "testMe0@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer0);
        Customer customer1 = new Customer("testMe", "TestMe", "2944811932", "testMe1@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer1);
        Customer customer2 = new Customer("testMe", "TestMe", "2935811932", "testMe2@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer2);

        List<Customer> customer = testMe.findAll();

        assertThat(customer).hasSize(3).contains(customer0, customer1, customer2);
    }

    @Test
    void existsByEmail() {
        Customer customer0 = new Customer("testMe", "TestMe", "2934811932", "testMe0@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer0);
        Customer customer1 = new Customer("testMe", "TestMe", "2944811932", "testMe1@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer1);

        boolean exists = testMe.existsByEmail("testMe1@gmail.com");

        assertThat(exists).isTrue();
    }

    @Test
    void getByEmail() {
        Customer customer0 = new Customer("testMe", "TestMe", "2934811932", "testMe0@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer0);
        Customer customer1 = new Customer("testMe", "TestMe", "2944811932", "testMe1@gmail.com", "testMePassword",
                "082395", new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);
        entityManager.persist(customer1);

        Customer customer = testMe.getByEmail("testMe1@gmail.com");

        assertThat(customer).isEqualTo(customer1);
    }
}
