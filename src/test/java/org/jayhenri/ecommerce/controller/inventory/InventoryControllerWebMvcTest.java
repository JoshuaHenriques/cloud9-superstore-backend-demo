package org.jayhenri.ecommerce.controller.inventory;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.jayhenri.ecommerce.controller.InventoryController;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
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

/**
 * The type Inventory controller web mvc test.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    private Inventory inventory;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        inventory = new Inventory("Test Product", 369, new Item("Test Product", "Item Description", 32.54));
    }

    /**
     * As json string string.
     *
     * @param obj the obj
     * @return the string
     */
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update item.
     *
     * @throws Exception the exception
     */
    @Test
    void updateItem() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        mockMvc.perform(
                put("/api/inventory/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inventory)))
                .andExpect(status().isOk());
    }

    /**
     * Update item throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void updateItemThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(false);

        mockMvc.perform(
                put("/api/inventory/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inventory)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Update item throws invalid item exception.
     *
     * @throws Exception the exception
     */
    @Test
    void updateItemThrowsInvalidItemException() throws Exception {

        mockMvc.perform(put("/api/inventory/update").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Add item to inventory.
     *
     * @throws Exception the exception
     */
    @Test
    void addItemToInventory() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(false);

        mockMvc.perform(
                post("/api/inventory/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inventory)))
                .andExpect(status().isCreated());
    }

    /**
     * Add item to inventory throws item already exists exception.
     *
     * @throws Exception the exception
     */
    @Test
    void addItemToInventoryThrowsItemAlreadyExistsException() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        mockMvc.perform(
                post("/api/inventory/add").contentType(MediaType.APPLICATION_JSON).content(asJsonString(inventory)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Add item to inventory throws invalid item exception.
     *
     * @throws Exception the exception
     */
    @Test
    @Disabled
    void addItemToInventoryThrowsInvalidItemException() throws Exception {

        mockMvc.perform(post("/api/inventory/add").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest());
    }

    /**
     * Gets by product name.
     *
     * @throws Exception the exception
     */
    @Test
    void getByProductName() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        mockMvc.perform((get("/api/inventory/get/Test Product"))).andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Test Product"));
    }

    /**
     * Gets by product name throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void getByProductNameThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        mockMvc.perform((get("/api/inventory/get/Test Product"))).andExpect(status().isBadRequest());
    }

    /**
     * Remove item to inventory.
     *
     * @throws Exception the exception
     */
    @Test
    void removeItemToInventory() throws Exception {
        given(inventoryService.existsByProductName("Test Project")).willReturn(true);

        mockMvc.perform(delete("/api/inventory/remove/Test Project")).andExpect(status().isOk());
    }

    /**
     * Remove item to inventory throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void removeItemToInventoryThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Project")).willReturn(false);

        mockMvc.perform(delete("/api/inventory/remove/Test Project")).andExpect(status().isBadRequest());
    }

    /**
     * Remove item to inventory throws invalid item exception.
     *
     * @throws Exception the exception
     */
    @Test
    void removeItemToInventoryThrowsInvalidItemException() throws Exception {

        mockMvc.perform(delete("/api/inventory/remove/").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    /**
     * Find all.
     *
     * @throws Exception the exception
     */
    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/inventory/items/list")).andExpect(status().isOk());
    }
}