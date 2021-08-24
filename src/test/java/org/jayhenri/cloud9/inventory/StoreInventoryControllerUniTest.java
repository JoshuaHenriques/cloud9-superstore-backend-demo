package org.jayhenri.cloud9.inventory;

import org.jayhenri.cloud9.controller.inventory.StoreInventoryController;
import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
import org.jayhenri.cloud9.model.inventory.StoreInventory;
import org.jayhenri.cloud9.model.item.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
 * The type StoreInventory controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class StoreInventoryControllerUniTest {

    private static final int quantity = 3088;
    @Captor
    private ArgumentCaptor<Item> captorItem;
    @Captor
    private ArgumentCaptor<Integer> captorInt;
    @Captor
    private ArgumentCaptor<Double> captorDouble;
    @Captor
    private ArgumentCaptor<String> captorString;
    @Captor
    private ArgumentCaptor<UUID> captorUUID;
    @Captor
    private ArgumentCaptor<StoreInventory> captorStoreInventory;
    @Mock
    private InventoryServiceI<StoreInventory> storeInventoryService;
    private InventoryControllerI<StoreInventory> storeInventoryController;
    private StoreInventory inventory;
    private UUID uuid;
    private Item item;

    private

    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        storeInventoryController = new StoreInventoryController(storeInventoryService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );

        inventory = new StoreInventory(
                item,
                item.getItemName(),
                3950,
                1129.99
        );
    }

    /**
     * Add item.
     *
     * @throws ItemAlreadyExistsException the item already exists exception
     * @throws InvalidItemException       the invalid item exception
     */
//    @Test
//    @Disabled
//    void addItem() throws ItemAlreadyExistsException, InvalidItemException {
//
//        given(storeInventoryService.existsByItemName(item.getItemName())).willReturn(false);
//
//        // ResponseEntity<String> response = storeInventoryController.add(item, uuid, quantity, item.getPrice());
//
//        // then(storeInventoryService).should().add(captorItem.capture(), captorInt.capture(), captorDouble.capture());
//
//        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
//        assertThat(captorItem.getValue()).isEqualTo(item);
//        assertThat(captorInt.getValue()).isEqualTo(quantity);
//        assertThat(captorDouble.getValue()).isEqualTo(item.getPrice());
//    }

    /**
     * Add item throws invalid item exception.
     */
    @Test
    @Disabled
    void addItemThrowsInvalidItemException() {
        // assertThrows(InvalidItemException.class, () -> storeInventoryController.add(null, null, 0, 0.00));
    }

    /**
     * Add item throws item already exists exception.
     */
//    @Test
//    void addItemThrowsItemAlreadyExistsException() {
//        given(storeInventoryService.existsByItemName(inventory.getItemName())).willReturn(true);
//
//        assertThrows(ItemAlreadyExistsException.class, () -> storeInventoryController.add(item, uuid, quantity, item.getPrice() * quantity));
//    }

    /**
     * Update item.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
//    @Test
//    void update() throws InvalidItemException, ItemNotFoundException {
//        given(storeInventoryService.existsByItemName(inventory.getItemName())).willReturn(true);
//
//        assertThat(HttpStatus.OK).isEqualTo(storeInventoryController.update(inventory).getStatusCode());
//
//        then(storeInventoryService).should().update(captorStoreInventory.capture());
//
//        assertThat(captorStoreInventory.getValue()).isEqualTo(inventory);
//    }

    /**
     * Update item throws invalid item exception.
     */
//    @Test
//    void updateItemThrowsInvalidItemException() {
//        assertThrows(InvalidItemException.class, () -> storeInventoryController.update(null));
//    }

    /**
     * Update item throws item not found exception.
     */
//    @Test
//    void updateItemThrowsItemNotFoundException() {
//        given(storeInventoryService.existsByItemName(inventory.getItemName())).willReturn(false);
//
//        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.update(inventory));
//    }

    /**
     * Gets by product name.
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
     * Gets by product name throws item not found exception.
     */
    @Test
    void getByItemNameThrowsItemNotFoundException() {
        given(storeInventoryService.existsByItemName("Test")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.getByItemName("Test"));
    }

    /**
     * Gets by product name.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getById() throws ItemNotFoundException {
        given(storeInventoryService.existsById(uuid)).willReturn(true);
        given(storeInventoryService.getById(uuid)).willReturn(inventory);

        ResponseEntity<StoreInventory> _inventory = storeInventoryController.getById(uuid);

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(storeInventoryService).should().getById(captorUUID.capture());

        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by product name throws item not found exception.
     */
    @Test
    void getByIdThrowsItemNotFoundException() {
        given(storeInventoryService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.getById(uuid));
    }

    /**
     * Remove item.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void removeInventory() throws InvalidItemException, ItemNotFoundException {
        given(storeInventoryService.existsById(uuid)).willReturn(true);
        given(storeInventoryService.getById(uuid)).willReturn(inventory);

        assertThat(HttpStatus.OK).isEqualTo(storeInventoryController.delete(uuid).getStatusCode());

        then(storeInventoryService).should().delete(inventory);
    }

    /**
     * Remove item throws invalid item exception.
     */
    @Test
    void removeInventoryThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> storeInventoryController.delete(null));
    }

    /**
     * Remove item throws item not found exception.
     */
    @Test
    void removeInventoryThrowsItemNotFoundException() {
        given(storeInventoryService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> storeInventoryController.delete(uuid));
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {

        assertThat(storeInventoryController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}