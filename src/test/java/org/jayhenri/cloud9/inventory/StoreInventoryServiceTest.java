package org.jayhenri.cloud9.inventory;

import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.jayhenri.cloud9.repository.inventory.StoreInventoryRepository;
import org.jayhenri.cloud9.service.inventory.StoreInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type StoreInventory service test.
 */
@ExtendWith(MockitoExtension.class)
class StoreInventoryServiceTest {

    /**
     * The Test me.
     */
    private StoreInventoryService storeInventoryService;

    /**
     * The StoreInventory repository.
     */
    @Mock
    private StoreInventoryRepository storeInventoryRepository;

    /**
     * The Captor storeInventory.
     */
    @Captor
    private ArgumentCaptor<StoreInventory> captorStoreInventory;

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
     * The StoreInventory.
     */
    private StoreInventory storeInventory;

    private Item item;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        storeInventoryService = new StoreInventoryService(storeInventoryRepository);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                null
        );

        storeInventory = new StoreInventory(
                item,
                item.getItemName(),
                3950,
                1129.99
        );
    }

    /**
     * Test add.
     */
    @Test
    void add() {
        storeInventoryService.add(item, 3950, 1129.99);

        then(storeInventoryRepository).should().save(captorStoreInventory.capture());

        assertThat(captorStoreInventory.getValue().getItem()).isEqualTo(item);
        assertThat(captorStoreInventory.getValue().getQuantity()).isEqualTo(3950);
        assertThat(captorStoreInventory.getValue().getPrice()).isEqualTo(1129.99);
    }

    /**
     * Test update.
     */
    @Test
    void update() {
        storeInventoryService.update(storeInventory);

        then(storeInventoryRepository).should().save(captorStoreInventory.capture());

        assertThat(captorStoreInventory.getValue()).isEqualTo(storeInventory);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        storeInventoryService.delete(storeInventory);

        then(storeInventoryRepository).should().delete(captorStoreInventory.capture());

        assertThat(captorStoreInventory.getValue()).isEqualTo(storeInventory);
    }

    /**
     * Find all.
     */
    @Test
    void findAllInventories() {
        storeInventoryService.findAll();

        then(storeInventoryRepository).should().findAll();
    }

    @Test
    void existsById() {

        given(storeInventoryRepository.existsById(uuid)).willReturn(true);

        boolean exists = storeInventoryService.existsById(uuid);

        then(storeInventoryRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    @Test
    void doesNotExistsById() {

        given(storeInventoryRepository.existsById(uuid)).willReturn(false);

        boolean exists = storeInventoryService.existsById(uuid);

        then(storeInventoryRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    @Test
    void getById() {

        given(storeInventoryRepository.getById(uuid)).willReturn(storeInventory);

        StoreInventory _storeInventory = storeInventoryService.getById(uuid);

        then(storeInventoryRepository).should().getById(captorUUID.capture());

        assertThat(_storeInventory).isEqualTo(storeInventory);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Exists by item name.
     */
    @Test
    void existsByItemName() {
        given(storeInventoryRepository.existsByItemName("iPhone 13"))
                .willReturn(true);

        Boolean bool = storeInventoryService.existsByItemName("iPhone 13");
        then(storeInventoryRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by item name.
     */
    @Test
    void doesNotExistsByItemName() {
        given(storeInventoryRepository.existsByItemName("iPhone 13"))
                .willReturn(false);

        Boolean bool = storeInventoryService.existsByItemName("iPhone 13");
        then(storeInventoryRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isFalse();
    }

    /**
     * Gets by product name.
     */
    @Test
    void getByItemName() {
        storeInventoryService.getByItemName("iPhone 13");

        then(storeInventoryRepository).should().getByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
    }
}