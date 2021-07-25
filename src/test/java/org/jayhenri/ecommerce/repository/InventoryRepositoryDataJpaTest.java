package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InventoryRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManger;

    @Autowired
    private InventoryRepository testMe;

    private Inventory inventory;

    private Customer customer;

    private CreditCard creditCard;

    private OrderDetails orderDetails;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        customer = new Customer("testMe", "TestMe", "2934811932", "testMe@gmail.com", "testMePassword", "082395",
                new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);

        inventory = new Inventory("Test Product", 369, new Item("Test Product", "Item Description", 32.54));

        creditCard = new CreditCard("Test Name", "4656085451464353", "05/23", "231", "4353");

        orderDetails = new OrderDetails("TEST", "TestMe@gmail.com", new ArrayList<>(), 43.24);
    }

    @Test
    void databaseShouldBeEmpty() {
        List<Inventory> inventory = testMe.findAll();

        assertThat(inventory).isEmpty();
    }
}
