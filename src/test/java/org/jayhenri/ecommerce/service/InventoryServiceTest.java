package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

/**
 * The type Inventory service test.
 */
@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    /**
     * The Test me.
     */
    private InventoryService testMe;

    /**
     * The Inventory repository.
     */
    @Mock
    private InventoryRepository inventoryRepository;

    /**
     * The Captor inventory.
     */
    @Captor
    private ArgumentCaptor<Inventory> captorInventory;

    /**
     * The Captor string.
     */
    @Captor
    private ArgumentCaptor<String> captorString;

    /**
     * The Inventory.
     */
    private Inventory inventory;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        testMe = new InventoryService(inventoryRepository);

        Item item = new Item(
                "Test Product",
                "Test Description",
                9.99
        );

        inventory = new Inventory(
                "Test Product",
                329,
                item
        );
    }

    /**
     * Test add.
     */
    @Test
    void add() {
        testMe.add(this.inventory);

        then(inventoryRepository).should().save(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    /**
     * Test update.
     */
    @Test
    void update() {
        testMe.update(this.inventory);

        then(inventoryRepository).should().save(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        testMe.delete(this.inventory);

        then(inventoryRepository).should().delete(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {
        testMe.findAll();

        then(inventoryRepository).should().findAll();
    }

    /**
     * Exists by product name.
     */
    @Test
    void existsByProductName() {
        given(inventoryRepository.existsByProductName("Test Product"))
                .willReturn(true);

        Boolean bool = testMe.existsByProductName("Test Product");
        then(inventoryRepository).should().existsByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by product name.
     */
    @Test
    void doesNotExistsByProductName() {
        given(inventoryRepository.existsByProductName("Test Product"))
                .willReturn(false);

        Boolean bool = testMe.existsByProductName("Test Product");
        then(inventoryRepository).should().existsByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
        assertThat(bool).isFalse();
    }

    /**
     * Gets by product name.
     */
    @Test
    void getByProductName() {
        testMe.getByProductName("Test Product");

        then(inventoryRepository).should().getByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
    }
}