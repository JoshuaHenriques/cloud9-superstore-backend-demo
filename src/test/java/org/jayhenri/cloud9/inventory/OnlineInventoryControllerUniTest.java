package org.jayhenri.cloud9.inventory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import java.util.HashSet;
import java.util.UUID;

import org.jayhenri.cloud9.controller.inventory.OnlineInventoryController;
import org.jayhenri.cloud9.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidInventoryException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.interfaces.service.other.ItemServiceI;
import org.jayhenri.cloud9.model.inventory.OnlineInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The type OnlineInventory controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class OnlineInventoryControllerUniTest {

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Captor
    private ArgumentCaptor<OnlineInventory> captorInventory;

    @Mock
    private InventoryServiceI<OnlineInventory> onlineInventoryService;

    @Mock
    private ItemServiceI itemService;

    private InventoryControllerI<OnlineInventory> onlineInventoryController;

    private OnlineInventory inventory;

    private UUID itemId, inventoryId;

    private Item item;

    private

    @BeforeEach void setUp() {

        itemId = UUID.randomUUID();

        inventoryId = UUID.randomUUID();

        onlineInventoryController = new OnlineInventoryController(onlineInventoryService, itemService);

        item = new Item("iPhone 13 Pro", "2021 Model", new HashSet<>(), 1299.99, null);

        inventory = new OnlineInventory(item, item.getItemName(), 3950, 1129.99);
    }

    /**
     * Add item.
     *
     * @throws InventoryAlreadyExistsException the item already exists exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException
     * @throws InvalidInventoryException
     */
    @Test
    void add() throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException,
            InvalidInventoryException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(onlineInventoryService.existsById(itemId)).willReturn(false);

        assertThat(HttpStatus.CREATED).isEqualTo(onlineInventoryController.add(inventory, itemId).getStatusCode());

        then(onlineInventoryService).should().add(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(captorInventory.getValue());
    }

    /**
     * Add item throws invalid item exception.
     */
    @Test
    void addThrowsInvalidInventoryException() {
        
        assertThrows(InvalidInventoryException.class, () -> onlineInventoryController.add(null, null));
    }

    /**
     * Add item throws invalid item exception.
     */
    @Test
    void addThrowsInventoryAlreadyExistsException() {

        given(itemService.existsById(itemId)).willReturn(true);
        given(onlineInventoryService.existsById(itemId)).willReturn(true);

        assertThrows(InventoryAlreadyExistsException.class, () -> onlineInventoryController.add(inventory, itemId));
    }

    /**
     * Add item throws item already exists exception.
     */
    @Test
    void addThrowsItemNotFoundException() {
        
        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> onlineInventoryController.add(inventory, itemId));
    }

    /**
     * Update item.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void update() throws InvalidItemException, ItemNotFoundException {
        
        given(onlineInventoryService.existsById(inventoryId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.update(inventory, inventoryId).getStatusCode());

        then(onlineInventoryService).should().update(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(inventory);
    }

    /**
     * Update item throws invalid item exception.
     */
    @Test
    void updateInventoryThrowsInvalidItemException() {
        
        assertThrows(InvalidItemException.class, () -> onlineInventoryController.update(null, null));
    }

    /**
     * Update item throws item not found exception.
     */
    @Test
    void updateInventoryThrowsItemNotFoundException() {
        
        given(onlineInventoryService.existsById(inventoryId)).willReturn(false);
        
        assertThrows(ItemNotFoundException.class, () ->
        onlineInventoryController.update(inventory, inventoryId));
    }

        /**
     * Remove item.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void delete() throws InvalidItemException, ItemNotFoundException {
        given(onlineInventoryService.existsById(inventoryId)).willReturn(true);
        given(onlineInventoryService.getById(inventoryId)).willReturn(inventory);

        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.delete(inventoryId).getStatusCode());

        then(onlineInventoryService).should().delete(inventory);
    }

    /**
     * Remove item throws item not found exception.
     */
    @Test
    void deleteThrowsItemNotFoundException() {
        given(onlineInventoryService.existsById(inventoryId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> onlineInventoryController.delete(inventoryId));
    }

    /**
     * Gets by product name.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getByItemName() throws ItemNotFoundException {
        given(onlineInventoryService.existsByItemName("Test")).willReturn(true);
        given(onlineInventoryService.getByItemName("Test")).willReturn(inventory);

        ResponseEntity<OnlineInventory> _inventory = onlineInventoryController.getByItemName("Test");

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(onlineInventoryService).should().getByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test");
    }

    /**
     * Gets by product name throws item not found exception.
     */
    @Test
    void getByItemNameThrowsItemNotFoundException() {
        given(onlineInventoryService.existsByItemName("Test")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> onlineInventoryController.getByItemName("Test"));
    }

    /**
     * Gets by product name.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getById() throws ItemNotFoundException {
        given(onlineInventoryService.existsById(inventoryId)).willReturn(true);
        given(onlineInventoryService.getById(inventoryId)).willReturn(inventory);

        ResponseEntity<OnlineInventory> _inventory = onlineInventoryController.getById(inventoryId);

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(onlineInventoryService).should().getById(captorUUID.capture());

        assertThat(captorUUID.getValue()).isEqualTo(inventoryId);
    }

    /**
     * Gets by product name throws item not found exception.
     */
    @Test
    void getByIdThrowsItemNotFoundException() {
        given(onlineInventoryService.existsById(inventoryId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> onlineInventoryController.getById(inventoryId));
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {

        assertThat(onlineInventoryController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}