package org.jayhenri.cloud9.item;

import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.jayhenri.store_manager.repository.item.ItemRepository;
import org.jayhenri.store_manager.service.item.ItemService;
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
 * The type Item service test.
 */
@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    private Item item;

    private ItemServiceI itemService;

    @Mock
    private ItemRepository itemRepository;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        itemService = new ItemService(itemRepository);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );
    }

    /**
     * Add.
     */
    @Test
    void add() {

        itemService.add(item);

        then(itemRepository).should().save(captorItem.capture());

        assertThat(item).isEqualTo(captorItem.getValue());
    }

    /**
     * Update.
     */
    @Test
    void update() {
        itemService.update(item);

        then(itemRepository).should().save(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        itemService.remove(item);

        then(itemRepository).should().delete(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Find all items.
     */
    @Test
    void findAllItems() {
        itemService.findAll();

        then(itemRepository).should().findAll();
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        given(itemRepository.existsById(uuid)).willReturn(true);

        boolean exists = itemService.existsById(uuid);

        then(itemRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isTrue();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        given(itemRepository.existsById(uuid)).willReturn(false);

        boolean exists = itemService.existsById(uuid);

        then(itemRepository).should().existsById(captorUUID.capture());

        assertThat(exists).isFalse();
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        given(itemRepository.getById(uuid)).willReturn(item);

        Item _item = itemService.getById(uuid);

        then(itemRepository).should().getById(captorUUID.capture());

        assertThat(_item).isEqualTo(item);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Exists by item name.
     */
    @Test
    void existsByItemName() {
        given(itemRepository.existsByItemName("iPhone 13"))
                .willReturn(true);

        Boolean bool = itemService.existsByItemName("iPhone 13");
        then(itemRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isTrue();
    }

    /**
     * Does not exists by item name.
     */
    @Test
    void doesNotExistsByItemName() {
        given(itemRepository.existsByItemName("iPhone 13"))
                .willReturn(false);

        Boolean bool = itemService.existsByItemName("iPhone 13");
        then(itemRepository).should().existsByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
        assertThat(bool).isFalse();
    }

    /**
     * Gets by item name.
     */
    @Test
    void getByItemName() {
        itemService.getByItemName("iPhone 13");

        then(itemRepository).should().getByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("iPhone 13");
    }
}
