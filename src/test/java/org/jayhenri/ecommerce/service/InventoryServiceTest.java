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

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    InventoryService testMe;

    @Mock
    InventoryRepository inventoryRepository;

    @Captor
    ArgumentCaptor<Inventory> captorInventory;

    @Captor
    ArgumentCaptor<String> captorString;

    Inventory inventory;
    Item item;

    @BeforeEach
    void setUp() {
        testMe = new InventoryService(inventoryRepository);

        item = new Item(
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

    @Test
    void testAdd() {
        testMe.add(this.inventory);

        then(inventoryRepository).should().save(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    @Test
    void testUpdate() {
        testMe.update(this.inventory);

        then(inventoryRepository).should().save(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    @Test
    void delete() {
        testMe.delete(this.inventory);

        then(inventoryRepository).should().delete(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(this.inventory);
    }

    @Test
    void findAll() {
        testMe.findAll();

        then(inventoryRepository).should().findAll();
    }

    @Test
    void existsByProductName() {
        given(testMe.existsByProductName("Test Product"))
                .willReturn(true);

        Boolean bool = testMe.existsByProductName("Test Product");
        then(inventoryRepository).should().existsByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
        assertThat(bool).isTrue();
    }

    @Test
    void doesNotExistsByProductName() {
        given(testMe.existsByProductName("Test Product"))
                .willReturn(false);

        Boolean bool = testMe.existsByProductName("Test Product");
        then(inventoryRepository).should().existsByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
        assertThat(bool).isFalse();
    }

    @Test
    void getByProductName() {
        testMe.getByProductName("Test Product");

        then(inventoryRepository).should().getByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test Product");
    }
}