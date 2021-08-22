package org.jayhenri.cloud9.inventory;

import org.jayhenri.cloud9.controller.inventory.OnlineInventoryController;
import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.other.InventoryControllerI;
import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
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

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type OnlineInventory controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class OnlineInventoryControllerUniTest {

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
    private ArgumentCaptor<OnlineInventory> captorOnlineInventory;

    @Mock
    private InventoryServiceI<OnlineInventory> onlineInventoryService;

    private InventoryControllerI<OnlineInventory> onlineInventoryController;

    private OnlineInventory inventory;

    private UUID uuid;

    private Item item;

    private static final int quantity = 3088;

    private

    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();

        onlineInventoryController = new OnlineInventoryController(onlineInventoryService);

        item = new Item(
                "iPhone 13 Pro",
                "2021 Model",
                new HashSet<>(),
                1299.99,
                null
        );

        inventory = new OnlineInventory(
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
    @Test
    void addInventory() throws ItemAlreadyExistsException, InvalidItemException {

        given(onlineInventoryService.existsByItemName(item.getItemName())).willReturn(false);

        ResponseEntity<String> response = onlineInventoryController.add(item, uuid, quantity, item.getPrice());

        then(onlineInventoryService).should().add(captorItem.capture(), captorInt.capture(), captorDouble.capture());

        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
        assertThat(captorItem.getValue()).isEqualTo(item);
        assertThat(captorInt.getValue()).isEqualTo(quantity);
        assertThat(captorDouble.getValue()).isEqualTo(item.getPrice());
    }

    /**
     * Add item throws invalid item exception.
     */
    @Test
    void addInventoryThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            onlineInventoryController.add(null, null, 0, 0.00);
        });
    }

    /**
     * Add item throws item already exists exception.
     */
    @Test
    void addInventoryThrowsItemAlreadyExistsException() {
        given(onlineInventoryService.existsByItemName(inventory.getItemName())).willReturn(true);

        assertThrows(ItemAlreadyExistsException.class, () -> {
            onlineInventoryController.add(item, uuid, quantity, item.getPrice()*quantity);
        });
    }

    /**
     * Update item.
     *
     * @throws InvalidItemException       the invalid item exception
     * @throws ItemNotFoundException      the item not found exception
     */
    @Test
    void update() throws InvalidItemException, ItemNotFoundException {
        given(onlineInventoryService.existsByItemName(inventory.getItemName())).willReturn(true);

        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.update(inventory).getStatusCode());

        then(onlineInventoryService).should().update(captorOnlineInventory.capture());

        assertThat(captorOnlineInventory.getValue()).isEqualTo(inventory);
    }

    /**
     * Update item throws invalid item exception.
     */
    @Test
    void updateInventoryThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            onlineInventoryController.update(null);
        });
    }

    /**
     * Update item throws item not found exception.
     */
    @Test
    void updateInventoryThrowsItemNotFoundException() {
        given(onlineInventoryService.existsByItemName(inventory.getItemName())).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            onlineInventoryController.update(inventory);
        });
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

        assertThrows(ItemNotFoundException.class, () -> {
            onlineInventoryController.getByItemName("Test");
        });
    }

    /**
     * Gets by product name.
     *
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void getById() throws ItemNotFoundException {
        given(onlineInventoryService.existsById(uuid)).willReturn(true);
        given(onlineInventoryService.getById(uuid)).willReturn(inventory);

        ResponseEntity<OnlineInventory> _inventory = onlineInventoryController.getById(uuid);

        assertThat(inventory).isEqualTo(_inventory.getBody());

        then(onlineInventoryService).should().getById(captorUUID.capture());

        assertThat(captorUUID.getValue()).isEqualTo(uuid);
    }

    /**
     * Gets by product name throws item not found exception.
     */
    @Test
    void getByIdThrowsItemNotFoundException() {
        given(onlineInventoryService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            onlineInventoryController.getById(uuid);
        });
    }

    /**
     * Remove item.
     *
     * @throws InvalidItemException  the invalid item exception
     * @throws ItemNotFoundException the item not found exception
     */
    @Test
    void removeInventory() throws InvalidItemException, ItemNotFoundException {
        given(onlineInventoryService.existsById(uuid)).willReturn(true);
        given(onlineInventoryService.getById(uuid)).willReturn(inventory);

        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.delete(uuid).getStatusCode());

        then(onlineInventoryService).should().delete(inventory);
    }

    /**
     * Remove item throws invalid item exception.
     */
    @Test
    void removeInventoryThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            onlineInventoryController.delete(null);
        });
    }

    /**
     * Remove item throws item not found exception.
     */
    @Test
    void removeInventoryThrowsItemNotFoundException() {
        given(onlineInventoryService.existsById(uuid)).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            onlineInventoryController.delete(uuid);
        });
    }

    /**
     * Find all.
     */
    @Test
    void findAll() {

        assertThat(onlineInventoryController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}