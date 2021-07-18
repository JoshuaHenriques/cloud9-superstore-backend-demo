package org.jayhenri.ecommerce.controller;

import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Integration Test
@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerUniTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository inventoryRepository;


    @BeforeEach
    void setUp() {
        inventoryService = new InventoryService(inventoryRepository);
    }

    @Test
    @Disabled
    void updateItem() {
    }

    @Test
    @Disabled
    void addItemToInventory() {
    }

    @Test
    @Disabled
    void getByProductName() {
    }

    @Test
    @Disabled
    void removeItemToInventory() {
    }

    /*
        @GetMapping(value = "/items/list")
    public List<Inventory> findAll() {

        return inventoryService.findAll();
    }
     */

    @Test
    @Disabled
    void findAll() throws Exception {
        List<Inventory> list = new ArrayList<>();
        list.add(new Inventory(
                "Test Product",
                369,
                new Item(
                        "Test Product",
                        "Item Description",
                        32.54
                )));

        given(inventoryService.findAll()).willReturn(list);

        mockMvc.perform(get("/api/inventory/items/list")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}