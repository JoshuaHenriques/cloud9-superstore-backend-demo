package org.jayhenri.cloud9.inventory;

import org.jayhenri.store_manager.interfaces.service.other.InventoryServiceI;
import org.jayhenri.store_manager.model.inventory.OnlineInventory;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.repository.inventory.OnlineInventoryRepository;
import org.jayhenri.store_manager.service.inventory.OnlineInventoryService;
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
 * The type OnlineInventory service test.
 */
@ExtendWith(MockitoExtension.class)
class OnlineInventoryServiceTest {

    /**
     * The Test me.
     */
    private InventoryServiceI<OnlineInventory> onlineInventoryService;

    /**
     * The OnlineInventory repository.
     */
    @Mock
    private OnlineInventoryRepository onlineInventoryRepository;

    /**
     * The Captor onlineInventory.
     */
    @Captor
    private ArgumentCaptor<OnlineInventory> captorOnlineInventory;

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

    private UUID uuid;

    /**
     * The OnlineInventory.
     */
    private OnlineInventory onlineInventory;

    private Item item;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        onlineInventoryService = new OnlineInventoryService(onlineInventoryRepository);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );

        onlineInventory = new OnlineInventory(
                item,
                item.getItemName(),
                3950,
                1129.99
        );
    }

    /**
     * Test add.
     */
//    @Test
//    void add() {
//        onlineInventoryService.add(item, 3950, 1129.99);
//
//        then(onlineInventoryRepository).should().save(captorOnlineInventory.capture());
//
//        assertThat(captorOnlineInventory.getValue().getItem()).isEqualTo(item);
//        assertThat(captorOnlineInventory.getValue().getQuantity()).isEqualTo(3950);
//        assertThat(captorOnlineInventory.getValue().getPrice()).isEqualTo(1129.99);
//    }

    /**
     * Test update.
     */
    @Test
    void update() {
        onlineInventoryService.update(onlineInventory);

        then(onlineInventoryRepository).should().save(captorOnlineInventory.capture());

        assertThat(captorOnlineInventory.getValue()).isEqualTo(onlineInventory);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        onlineInventoryService.delete(onlineInventory);

        then(onlineInventoryRepository).should().delete(captorOnlineInventory.capture());

        assertThat(captorOnlineInventory.getValue()).isEqualTo(onlineInventory);
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {
        onlineInventoryService.findAll();

        then(onlineInventoryRepository).should().findAll();
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(onlineInventoryRepository.existsById(uuid)).willReturn(true);

        boolean exists = onlineInventoryService.existsById(uuid);

        then(onlineInventoryRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(onlineInventoryRepository.existsById(uuid)).willReturn(false);

        boolean exists = onlineInventoryService.existsById(uuid);

        then(onlineInventoryRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(onlineInventoryRepository.getById(uuid)).willReturn(onlineInventory);

        OnlineInventory _onlineInventory = onlineInventoryService.getById(uuid);

        then(onlineInventoryRepository).should().getById(captorUUID.capture());

        assertThat(_onlineInventory).isEqualTo(onlineInventory);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Exists by item name.
     */
    @Test
    void existsByItemName() {
        given(onlineInventoryRepository.existsByItemName("iPhone 13"))
                .willReturn(true);

        Boolean bool = onlineInventoryService.existsByItemName("iPhone 13");
        then(onlineInventoryRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by item name.
     */
    @Test
    void doesNotExistsByItemName() {
        given(onlineInventoryRepository.existsByItemName("iPhone 13"))
                .willReturn(false);

        Boolean bool = onlineInventoryService.existsByItemName("iPhone 13");
        then(onlineInventoryRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isFalse();
    }

    /**
     * Gets by product name.
     */
    @Test
    void getByItemName() {
        onlineInventoryService.getByItemName("iPhone 13");

        then(onlineInventoryRepository).should().getByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
    }
}