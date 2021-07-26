package org.jayhenri.ecommerce.repository;

import org.jayhenri.ecommerce.model.OrderDB;
import org.jayhenri.ecommerce.model.OrderDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Customer repository data jpa test.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrderDBRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderDBRepository testMe;

    /**
     * Database should be empty.
     */
    @Test
    void emptyDatabase() {
        List<OrderDB> list = testMe.findAll();
        assertThat(list).isEmpty();
    }

    /**
     * Database should store customer.
     */
    @Test
    void storeCustomer() {
        OrderDB orderDB = new OrderDB(new OrderDetails("TESTSTATUS", "testMe0@gmail.com", null, 29.99));

        OrderDB _orderDB = testMe.save(orderDB);

        assertThat(_orderDB).hasFieldOrPropertyWithValue("orderStatus", "TESTSTATUS");
        assertThat(_orderDB).hasFieldOrPropertyWithValue("customerEmail", "testMe0@gmail.com");
        assertThat(_orderDB).hasFieldOrPropertyWithValue("totalPrice", 38.948012999999996);
    }

    /**
     * Find all order db.
     */
    @Test
    void findAllOrderDB() {
        OrderDetails orderDetails0 = new OrderDetails("TESTSTATUS", "testMe0@gmail.com", null, 29.99);
        OrderDB orderDB0 = new OrderDB(orderDetails0);
        entityManager.persist(orderDB0);

        OrderDetails orderDetails1 = new OrderDetails("TESTSTATUS", "testMe1@gmail.com", null, 29.99);
        OrderDB orderDB1 = new OrderDB(orderDetails1);
        entityManager.persist(orderDB1);

        OrderDetails orderDetails2 = new OrderDetails("TESTSTATUS", "testMe2@gmail.com", null, 29.99);
        OrderDB orderDB2 = new OrderDB(orderDetails2);
        entityManager.persist(orderDB2);

        List<OrderDB> list = testMe.findAll();

        assertThat(list).hasSize(3).contains(orderDB0, orderDB1, orderDB2);
    }
}
