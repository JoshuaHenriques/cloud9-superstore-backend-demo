package org.jayhenri.ecommerce.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * The type Inventory repository data jpa test.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class InventoryRepositoryDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventoryRepository testMe;

    private Inventory inventory;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        inventory = new Inventory("Test Product", 369, new Item("Test Product", "f", 334.3));
    }

    /**
     * Database should be empty.
     */
    @Test
    void emptyDatabase() {
        List<Inventory> inventory = testMe.findAll();
        assertThat(inventory).isEmpty();
    }

    /**
     * Database should store inventory.
     */
    @Test
    void storeInventory() {
        Inventory _inventory = testMe.save(inventory);

        assertThat(_inventory).hasFieldOrPropertyWithValue("productName", "Test Product");
        assertThat(_inventory).hasFieldOrPropertyWithValue("quantity", 369);
        assertThat(_inventory).hasFieldOrProperty("item");
    }

    @Test
    void findAllInventory() {
        Inventory inventory0 = new Inventory("inventory1", 369, null);
        entityManager.persist(inventory0);
        Inventory inventory1 = new Inventory("inventory2", 369, null);
        entityManager.persist(inventory1);
        Inventory inventory2 = new Inventory("inventory3", 369, null);
        entityManager.persist(inventory2);

        List<Inventory> inventory = testMe.findAll();

        assertThat(inventory).hasSize(3).contains(inventory0, inventory1, inventory2);
    }

    @Test
    void existsByProductName() {
        Inventory inventory0 = new Inventory("inventory0", 369, null);
        entityManager.persist(inventory0);
        Inventory inventory1 = new Inventory("inventory1", 369, null);
        entityManager.persist(inventory1);

        boolean exists = testMe.existsByProductName("inventory1");

        assertThat(exists).isTrue();
    }

    @Test
    void getByProductName() {
        Inventory inventory0 = new Inventory("inventory0", 369, null);
        entityManager.persist(inventory0);
        Inventory inventory1 = new Inventory("inventory1", 369, null);
        entityManager.persist(inventory1);

        Inventory inventory = testMe.getByProductName("inventory1");

        assertThat(inventory).isEqualTo(inventory1);
    }
}
