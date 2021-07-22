package org.jayhenri.ecommerce.controller.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jayhenri.ecommerce.controller.InventoryController;
import org.jayhenri.ecommerce.model.Inventory;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(InventoryController.class)
class InventoryControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory(
                "Test Product",
                369,
                new Item(
                        "Test Product",
                        "Item Description",
                        32.54
                ));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  

    @Test
    void updateItem() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        mockMvc.perform(put("/api/inventory/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(inventory)))
            .andExpect(status().isOk());
    }

    @Test
    void updateItemThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(false);

        mockMvc.perform(put("/api/inventory/update")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(inventory)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void updateItemThrowsInvalidItemException() throws Exception {

        mockMvc.perform(put("/api/inventory/update")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void addItemToInventory() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(false);

        mockMvc.perform(post("/api/inventory/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(inventory)))
            .andExpect(status().isOk());
    }

    @Test
    void addItemToInventoryThrowsItemAlreadyExistsException() throws Exception {
        given(inventoryService.existsByProductName(inventory.getProductName())).willReturn(true);

        mockMvc.perform(post("/api/inventory/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(inventory)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void addItemToInventoryThrowsInvalidItemException() throws Exception {

        mockMvc.perform(post("/api/inventory/add")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getByProductName() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);
                
        mockMvc.perform((get("/api/inventory/get/Test Product")))
            .andExpect(status().isOk());
    }

    @Test
    void getByProductNameThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);
                
        mockMvc.perform((get("/api/inventory/get/Test Product")))
            .andExpect(status().isBadRequest());
    }

    @Test
    void removeItemToInventory() throws Exception {
        given(inventoryService.existsByProductName("Test Project")).willReturn(true);

        mockMvc.perform(delete("/api/inventory/remove/Test Project"))
            .andExpect(status().isOk());
    }

    @Test
    void removeItemToInventoryThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Project")).willReturn(false);

        mockMvc.perform(delete("/api/inventory/remove/Test Project"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void removeItemToInventoryThrowsInvalidItemException() throws Exception {

        mockMvc.perform(delete("/api/inventory/remove/"))
            .andExpect(status().isNotFound());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/api/inventory/items/list"))
                .andExpect(status().isOk());
    }
}