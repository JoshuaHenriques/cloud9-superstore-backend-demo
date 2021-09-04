package org.jayhenri.cloud9.inventory;

import org.jayhenri.store_manager.controller.inventory.StoreInventoryController;
import org.jayhenri.store_manager.exception.alreadyexists.InventoryAlreadyExistsException;
import org.jayhenri.store_manager.exception.invalid.InvalidInventoryException;
import org.jayhenri.store_manager.exception.invalid.InvalidItemException;
import org.jayhenri.store_manager.exception.notfound.ItemNotFoundException;
import org.jayhenri.store_manager.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.store_manager.interfaces.service.other.InventoryServiceI;
import org.jayhenri.store_manager.interfaces.service.other.ItemServiceI;
import org.jayhenri.store_manager.model.inventory.StoreInventory;
import org.jayhenri.store_manager.model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Store inventory controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class StoreInventoryControllerUniTest {

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Captor
    private ArgumentCaptor<StoreInventory> captorInventory;

    @Mock
    private InventoryServiceI<StoreInventory> storeInventoryService;

    @Mock
    private ItemServiceI itemService;

    private InventoryControllerI<StoreInventory> storeInventoryController;

    private StoreInventory inventory;

    private UUID itemId, inventoryId;

    private Item item;

    private

    @BeforeEach
    void setUp() {

        itemId = UUID.randomUUID();

        inventoryId = UUID.randomUUID();

        storeInventoryController = new StoreInventoryController(storeInventoryService, itemService);

        item = new Item("iPhone 13 Pro", "2021 Model", new HashSet<>(), 1299.99, null);

        inventory = new StoreInventory(item, item.getItemName(), 3950, 1129.99);
    }

    /**
     * Add.
     *
     * @throws InventoryAlreadyExistsException the inventory already exists exception
     * @throws InvalidItemException            the invalid item exception
     * @throws ItemNotFoundException           the item not found exception
     * @throws InvalidInventoryException       the invalid inventory exception
     */
    @Test
    void add() throws InventoryAlreadyExistsException, InvalidItemException, ItemNotFoundException,
            InvalidInventoryException {

        given(itemService.existsById(itemId)).willReturn(true);
        given(storeInventoryService.existsById(itemId)).willReturn(false);

        assertThat(HttpStatus.CREATED).isEqualTo(storeInventoryController.add(inventory, itemId).getStatusCode());

        then(storeInventoryService).should().add(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(captorInventory.getValue());
    }

    /**
     * Add throws invalid inventory exception.
     */
    @Test
    void addThrowsInvalidInventoryException() {

        assertThrows(InvalidInventoryException.class, () -> storeInventoryController.add(null, null));
    }

    /**
     * Add throws inventory already exists exception.
     */
    @Test
    void addThrowsInventoryAlreadyExistsException() {

        given(itemService.existsById(itemId)).willReturn(true);
        given(storeInventoryService.existsById(itemId)).willReturn(true);

        assertThrows(InventoryAlreadyExistsException.class, () -> storeInventoryController.add(inventory, itemId));
    }

    /**
     * Add throws item not found exception.
     */
    @Test
    void addThrowsItemNotFoundException() {

        given(itemService.existsById(itemId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.add(inventory, itemId));
    }

    /**
     * Update.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void update() throws InvalidItemException, ItemNotFoundException {

        given(storeInventoryService.existsById(inventoryId)).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(storeInventoryController.update(inventory, inventoryId).getStatusCode());

        then(storeInventoryService).should().update(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(inventory);
    }

    /**
     * Update inventory throws invalid item exception.
     */
    @Test
    void updateInventoryThrowsInvalidItemException() {

        assertThrows(InvalidItemException.class, () -> storeInventoryController.update(null, null));
    }

    /**
     * Update inventory throws item not found exception.
     */
    @Test
    void updateInventoryThrowsItemNotFoundException() {

        given(storeInventoryService.existsById(inventoryId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () ->
                storeInventoryController.update(inventory, inventoryId));
    }

    /**
     * Delete.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void delete() throws InvalidItemException, ItemNotFoundException {
        given(storeInventoryService.existsById(inventoryId)).willReturn(true);
        given(storeInventoryService.getById(inventoryId)).willReturn(inventory);

        assertThat(HttpStatus.OK).isEqualTo(storeInventoryController.delete(inventoryId).getStatusCode());

        then(storeInventoryService).should().delete(inventory);
    }

    /**
     * Delete throws item not found exception.
     */
    @Test
    void deleteThrowsItemNotFoundException() {
        given(storeInventoryService.existsById(inventoryId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.delete(inventoryId));
    }

    /**
     * Gets by item name.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getByItemName() throws ItemNotFoundException {
        given(storeInventoryService.existsByItemName("Test")).willReturn(true);
        given(storeInventoryService.getByItemName("Test")).willReturn(inventory);

        ResponseEntity<StoreInventory> _inventory = storeInventoryController.getByItemName("Test");

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(storeInventoryService).should().getByItemName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test");
    }

    /**
     * Gets by item name throws item not found exception.
     */
    @Test
    void getByItemNameThrowsItemNotFoundException() {
        given(storeInventoryService.existsByItemName("Test")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.getByItemName("Test"));
    }

    /**
     * Gets by id.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getById() throws ItemNotFoundException {
        given(storeInventoryService.existsById(inventoryId)).willReturn(true);
        given(storeInventoryService.getById(inventoryId)).willReturn(inventory);

        ResponseEntity<StoreInventory> _inventory = storeInventoryController.getById(inventoryId);

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(storeInventoryService).should().getById(captorUUID.capture());

        assertThat(captorUUID.getValue()).isEqualTo(inventoryId);
    }

    /**
     * Gets by id throws item not found exception.
     */
    @Test
    void getByIdThrowsItemNotFoundException() {
        given(storeInventoryService.existsById(inventoryId)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.getById(inventoryId));
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {

        assertThat(storeInventoryController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}