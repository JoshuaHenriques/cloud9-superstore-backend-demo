package org.jayhenri.cloud9.customer.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;

import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.customer.Orders;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.repository.customer.CustomerRepository;
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
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CustomerRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        customer = new Customer(
                "test",
                new Login(
                        "Email",
                        "2923338282",
                        "password",
                        new String[] {"USER"},
                        true
                ),
                new Cart(
                        customer,
                        new HashSet<>()
                ),
                new Address(
                        "Street Name",
                        "303",
                        "000",
                        "City",
                        "Postal Code",
                        "Province"
                ),
                new HashSet<CreditCard>(),
                new HashSet<Orders>(),
                "test",
                "2221113333",
                "test.test@gmail.com",
                "01/01/1995"
        );
    }

    /**
     * Database should be empty.
     */
    @Test
    void emptyDatabase() {
        List<Customer> customer = customerRepository.findAll();
        assertThat(customer).isEmpty();
    }

//     /**
//      * Database should store customer.
//      */
//     @Test
//     void storeCustomer() {
//         Customer _customer = customerRepository.save(customer);

//         assertThat(_customer).hasFieldOrPropertyWithValue("email", "customerRepository@gmail.com");
//         assertThat(_customer).hasFieldOrPropertyWithValue("phoneNumber", "2934811932");
//         assertThat(_customer).hasFieldOrProperty("address");
//     }

//     /**
//      * Database should store customer.
//      */
//     @Test
//     void deleteCustomer() {
//         Customer customer0 = new Customer();
//         Customer _customer = entityManager.persist(customer0);

//         customerRepository.delete(_customer);
//         // entityManager.flush();

//         Customer __customer = entityManager.find(Customer.class, _customer.getCustomerUUID());
//         assertThat(__customer).isNull();
//     }

//     /**
//      * Find all inventory.
//      */
//     @Test
//     void findAllInventory() {
//         Customer customer0 = new Customer();
//         entityManager.persist(customer0);
//         Customer customer1 = new Customer();
//         entityManager.persist(customer1);
//         Customer customer2 = new Customer();
//         entityManager.persist(customer2);

//         List<Customer> customer = customerRepository.findAll();

//         assertThat(customer).hasSize(3).contains(customer0, customer1, customer2);
//     }

//     /**
//      * Exists by email.
//      */
//     @Test
//     void existsByEmail() {
//         Customer customer0 = new Customer();
//         entityManager.persist(customer0);
//         Customer customer1 = new Customer();
//         entityManager.persist(customer1);

//         boolean exists = customerRepository.existsByEmail("customerRepository1@gmail.com");

//         assertThat(exists).isTrue();
//     }

//     /**
//      * Gets by email.
//      */
//     @Test
//     void getByEmail() {
//         Customer customer0 = new Customer();
//         entityManager.persist(customer0);
//         Customer customer1 = new Customer();
//         entityManager.persist(customer1);

//         Customer customer = customerRepository.getByEmail("customerRepository1@gmail.com");

//         assertThat(customer).isEqualTo(customer1);
//     }
}
