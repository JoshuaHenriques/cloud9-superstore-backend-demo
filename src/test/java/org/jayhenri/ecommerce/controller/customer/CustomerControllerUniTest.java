package org.jayhenri.ecommerce.controller.customer;

import org.jayhenri.ecommerce.controller.InventoryController;
import org.jayhenri.ecommerce.exception.InvalidItemException;
import org.jayhenri.ecommerce.exception.ItemAlreadyExistsException;
import org.jayhenri.ecommerce.exception.ItemNotFoundException;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerControllerUniTest {

    @Captor
    private ArgumentCaptor<Inventory> captorInventory;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Mock
    private InventoryService inventoryService;

    private InventoryController testMe;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        testMe = new InventoryController(inventoryService);
    }

    @Test
    void updateItem() throws ItemAlreadyExistsException, InvalidItemException {
        assertThat(ResponseEntity.ok().build()).isEqualTo(testMe.updateItem(inventory));

        then(inventoryService).should().update(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(inventory);

    }

    @Test
    void updateItemThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            testMe.updateItem(null);
        });
    }

    @Test
    void updateItemThrowsItemAlreadyExistsException() {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        assertThrows(ItemAlreadyExistsException.class, () -> {
            testMe.updateItem(inventory);
        });
    }

    @Test
    void addItem() throws ItemAlreadyExistsException, InvalidItemException {
        assertThat(ResponseEntity.ok().build()).isEqualTo(testMe.addItem(inventory));

        then(inventoryService).should().add(captorInventory.capture());

        assertThat(captorInventory.getValue()).isEqualTo(inventory);

    }

    @Test
    void addItemThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            testMe.addItem(null);
        });
    }

    @Test
    void addItemThrowsItemAlreadyExistsException() {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        assertThrows(ItemAlreadyExistsException.class, () -> {
            testMe.addItem(inventory);
        });
    }

    @Test
    void getByProductName() throws ItemNotFoundException {
        given(inventoryService.existsByProductName("Test")).willReturn(true);
        given(inventoryService.getByProductName("Test")).willReturn(inventory);

        assertThat(inventory).isEqualTo(testMe.getByProductName("Test"));

        then(inventoryService).should().getByProductName(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("Test");
    }

    @Test
    void getByProductNameThrowsItemNotFoundException() {
        given(inventoryService.existsByProductName("Test")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.getByProductName("Test");
        });
    }

    @Test
    void removeItem() throws InvalidItemException, ItemNotFoundException {
        given(inventoryService.existsByProductName("Test")).willReturn(true);
        given(inventoryService.getByProductName("Test")).willReturn(inventory);

        assertThat(ResponseEntity.ok().build()).isEqualTo(testMe.removeItem("Test"));

        then(inventoryService).should().delete(inventory);
    }

    @Test
    void removeItemThrowsInvalidItemException() {
        assertThrows(InvalidItemException.class, () -> {
            testMe.removeItem(null);
        });
    }

    @Test
    void removeItemThrowsItemNotFoundException() {
        given(inventoryService.existsByProductName("Test")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.removeItem("Test");
        });
    }

    @Test
    void findAll() {
        assertThat(testMe.findAll()).isEqualTo(inventoryService.findAll());
    }
}