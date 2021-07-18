package org.jayhenri.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.repository.InventoryRepository;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

// Integration Test
@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerIntTest {

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

        MvcResult result = mockMvc.perform(get("/api/inventory/items/list"))
                .andExpect(status().isOk())
                .andReturn();
    }
}