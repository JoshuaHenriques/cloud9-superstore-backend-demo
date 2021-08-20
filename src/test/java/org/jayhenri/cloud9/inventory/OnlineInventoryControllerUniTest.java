//package org.jayhenri.cloud9.inventory;
//
//import org.jayhenri.cloud9.controller.inventory.OnlineInventoryController;
//import org.jayhenri.cloud9.exception.invalid.InvalidItemException;
//import org.jayhenri.cloud9.exception.alreadyexists.ItemAlreadyExistsException;
//import org.jayhenri.cloud9.exception.notfound.ItemNotFoundException;
//import org.jayhenri.cloud9.interfaces.service.other.InventoryServiceI;
//import org.jayhenri.cloud9.model.inventory.OnlineInventory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Captor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//
///**
// * The type OnlineInventory controller uni test.
// */
//@ExtendWith(MockitoExtension.class)
//class OnlineInventoryControllerUniTest {
//
//    @Captor
//    private ArgumentCaptor<OnlineInventory> captorInventory;
//
//    @Captor
//    private ArgumentCaptor<String> captorString;
//
//    @Mock
//    private InventoryServiceI<OnlineInventory> inventoryService;
//
//    private OnlineInventoryController onlineInventoryController;
//
//    private OnlineInventory inventory;
//
//    /**
//     * Add item.
//     *
//     * @throws ItemAlreadyExistsException the item already exists exception
//     * @throws InvalidItemException       the invalid item exception
//     */
//    @Test
//    void addItem() throws ItemAlreadyExistsException, InvalidItemException {
//        ResponseEntity<String> response = onlineInventoryController.add(inventory);
//
//        assertThat(HttpStatus.CREATED).isEqualTo(response.getStatusCode());
//
//        then(inventoryService).should().add(captorInventory.capture());
//
//        assertThat(captorInventory.getValue()).isEqualTo(inventory);
//
//    }
//
//    /**
//     * Add item throws invalid item exception.
//     */
//    @Test
//    void addItemThrowsInvalidItemException() {
//        assertThrows(InvalidItemException.class, () -> {
//            onlineInventoryController.add(null);
//        });
//    }
//
//    /**
//     * Add item throws item already exists exception.
//     */
//    @Test
//    void addItemThrowsItemAlreadyExistsException() {
//        given(inventoryService.existsByItemName(inventory.getItemName())).willReturn(true);
//
//        assertThrows(ItemAlreadyExistsException.class, () -> {
//            onlineInventoryController.add(inventory);
//        });
//    }
//
//    /**
//     * Sets up.
//     */
//    @BeforeEach
//    void setUp() {
//        inventory = new OnlineInventory();
//        onlineInventoryController = new OnlineInventoryController(inventoryService);
//    }
//
//    /**
//     * Update item.
//     *
//     * @throws InvalidItemException       the invalid item exception
//     * @throws ItemNotFoundException      the item not found exception
//     */
//    @Test
//    void update() throws InvalidItemException, ItemNotFoundException {
//        given(inventoryService.existsByItemName(inventory.getItemName())).willReturn(true);
//
//        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.update(inventory).getStatusCode());
//
//        then(inventoryService).should().update(captorInventory.capture());
//
//        assertThat(captorInventory.getValue()).isEqualTo(inventory);
//    }
//
//    /**
//     * Update item throws invalid item exception.
//     */
//    @Test
//    void updateItemThrowsInvalidItemException() {
//        assertThrows(InvalidItemException.class, () -> {
//            onlineInventoryController.update(null);
//        });
//    }
//
//    /**
//     * Update item throws item not found exception.
//     */
//    @Test
//    void updateItemThrowsItemNotFoundException() {
//        given(inventoryService.existsByItemName(inventory.getItemName())).willReturn(false);
//
//        assertThrows(ItemNotFoundException.class, () -> {
//            onlineInventoryController.update(inventory);
//        });
//    }
//
//    /**
//     * Gets by product name.
//     *
//     * @throws ItemNotFoundException the item not found exception
//     */
//    @Test
//    void getByItemName() throws ItemNotFoundException {
//        given(inventoryService.existsByItemName("Test")).willReturn(true);
//        given(inventoryService.getByItemName("Test")).willReturn(inventory);
//
//        ResponseEntity<OnlineInventory> _inventory = onlineInventoryController.getByItemName("Test");
//
//        assertThat(inventory).isEqualTo(_inventory.getBody());
//
//        then(inventoryService).should().getByItemName(captorString.capture());
//
//        assertThat(captorString.getValue()).isEqualTo("Test");
//    }
//
//    /**
//     * Gets by product name throws item not found exception.
//     */
//    @Test
//    void getByProductNameThrowsItemNotFoundException() {
//        given(inventoryService.existsByItemName("Test")).willReturn(false);
//
//        assertThrows(ItemNotFoundException.class, () -> {
//            onlineInventoryController.getByItemName("Test");
//        });
//    }
//
//    /**
//     * Remove item.
//     *
//     * @throws InvalidItemException  the invalid item exception
//     * @throws ItemNotFoundException the item not found exception
//     */
//    @Test
//    void removeItem() throws InvalidItemException, ItemNotFoundException {
//        given(inventoryService.existsByItemName("Test")).willReturn(true);
//        given(inventoryService.getByItemName("Test")).willReturn(inventory);
//
//        assertThat(HttpStatus.OK).isEqualTo(onlineInventoryController.removeItem("Test").getStatusCode());
//
//        then(inventoryService).should().delete(inventory);
//    }
//
//    /**
//     * Remove item throws invalid item exception.
//     */
//    @Test
//    void removeItemThrowsInvalidItemException() {
//        assertThrows(InvalidItemException.class, () -> {
//            onlineInventoryController.removeItem(null);
//        });
//    }
//
//    /**
//     * Remove item throws item not found exception.
//     */
//    @Test
//    void removeItemThrowsItemNotFoundException() {
//        given(inventoryService.existsByItemName("Test")).willReturn(false);
//
//        assertThrows(ItemNotFoundException.class, () -> {
//            onlineInventoryController.removeItem("Test");
//        });
//    }
//
//    /**
//     * Find all.
//     */
//    @Test
//    void findAll() {
//
//        assertThat(onlineInventoryController.findAll().getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
//}