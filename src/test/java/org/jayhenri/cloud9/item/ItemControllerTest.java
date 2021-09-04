package org.jayhenri.cloud9.item;

import org.jayhenri.store_manager.controller.item.ItemController;
import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.naming.InvalidNameException;
import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Item controller test.
 */
@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    private Item item;

    @Mock
    private ItemServiceI itemService;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    private ItemController itemController;

    private UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        itemController = new ItemController(itemService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );
    }

    /**
     * Add item.
     *
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
    @Test
    void addItem() throws ItemAlreadyExistsException, InvalidItemException {

        given(itemService.existsById(item.getItemUUID())).willReturn(false);
        given(itemService.existsByItemName(item.getItemName())).willReturn(false);

        assertThat(itemController.add(item).getStatusCode()).isEqualTo(HttpStatus.CREATED);

        then(itemService).should().add(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Add item throws invalid item exception.
     */
    @Test
    void addItemThrowsInvalidItemException() {

        assertThrows(InvalidItemException.class, () -> itemController.add(null));
    }

    /**
     * Add item throws item already exists exception.
     */
    @Test
    void addItemThrowsItemAlreadyExistsException() {

        given(itemService.existsByItemName(item.getItemName())).willReturn(true);

        assertThrows(ItemAlreadyExistsException.class, () -> itemController.add(item));
    }

    /**
     * Update item.
     *
     * @throws InventoryAlreadyExistsException the inventory already exists exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException           the item not found exception
     */
    @Test
    void updateItem() throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException {

        given(itemService.existsById(uuid)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(itemController.update(item, uuid).getStatusCode());

        then(itemService).should().update(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Update item throws invalid item exception.
     */
    @Test
    void updateItemThrowsInvalidItemException() {

        assertThrows(InvalidItemException.class, () -> itemController.add(null));
    }

    /**
     * Update item throws item not found exception.
     */
    @Test
    void updateItemThrowsItemNotFoundException() {

        given(itemService.existsByItemName(item.getItemName())).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> itemController.update(item, uuid));
    }

    /**
     * Delete item.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void deleteItem() throws ItemNotFoundException {

        given(itemService.existsById(uuid)).willReturn(true);
        given(itemService.getById(uuid)).willReturn(item);

        assertThat(HttpStatus.OK).isEqualTo(itemController.delete(uuid).getStatusCode());

        then(itemService).should().remove(captorItem.capture());

        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Delete item throws item not found exception.
     */
    @Test
    void deleteItemThrowsItemNotFoundException() {

        given(itemService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> itemController.delete(uuid));
    }

    /**
     * List.
     */
    @Test
    void list() {

    }

    /**
     * Get.
     *
     * @throws InvalidNameException  the invalid name exception
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void get() throws InvalidNameException, InvalidItemException, ItemNotFoundException {

        given(itemService.existsById(uuid)).willReturn(true);
        given(itemService.getById(uuid)).willReturn(item);

        assertThat(HttpStatus.OK).isEqualTo(itemController.get(uuid).getStatusCode());
        assertThat(item).isEqualTo(itemController.get(uuid).getBody());
    }

    /**
     * Gets item throws item not found exception.
     */
    @Test
    void getItemThrowsItemNotFoundException() {

        given(itemService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> itemController.get(uuid));
    }

    /**
     * Gets item throws invalid item exception.
     */
    @Test
    void getItemThrowsInvalidItemException() {

        assertThrows(InvalidItemException.class, () -> itemController.get(null));
    }
}
