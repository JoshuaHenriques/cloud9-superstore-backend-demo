package org.jayhenri.ecommerce.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jayhenri.ecommerce.controller.CustomerController;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.CustomerService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Customer controller web mvc test.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private InventoryService inventoryService;

    private Customer customer;

    private Inventory inventory;

    private CreditCard creditCard;

    private OrderDetails orderDetails;

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
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        customer = new Customer("testMe", "TestMe", "2934811932", "testMe@gmail.com", "testMePassword", "082395",
                new Address("Test Me", "29L", "0L", "New York", "T2K9R3", "Province"), null, null, null);

        inventory = new Inventory("Test Product", 369, new Item("Test Product", "Item Description", 32.54));

        orderDetails = new OrderDetails("TEST", "TestMe@gmail.com", new ArrayList<>(), 43.24);

        creditCard = new CreditCard("Test Name", "4656085451464353", "05/23", "231", "4353");
    }

    /**
     * Update item.
     *
     * @throws Exception the exception
     */
    @Test
    void updateCustomer() throws Exception {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(true);

        mockMvc.perform(
                put("/api/customers/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andExpect(status().isOk());
    }

    /**
     * Update item throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void updateCustomerThrowsItemNotFoundException() throws Exception {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(false);

        mockMvc.perform(
                put("/api/customers/update").contentType(MediaType.APPLICATION_JSON).content(asJsonString(customer)))
                .andExpect(status().isNotFound());
    }

    /**
     * Update item throws invalid item exception.
     *
     * @throws Exception the exception
     */
    @Test
    void updateCustomerThrowsInvalidCustomerException() throws Exception {

        mockMvc.perform(put("/api/customers/update").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    /**
     * Delete customer.
     *
     * @throws Exception the exception
     */
    @Test
    void deleteCustomer() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(delete("/api/customers/delete/testMe@gmail.com")).andExpect(status().isOk());
    }

    /**
     * Remove item to inventory throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void deleteCustomerThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("Test Project")).willReturn(false);

        mockMvc.perform(delete("/api/customers/delete/testMe@gmail.com")).andExpect(status().isNotFound());
    }

    /**
     * List customers.
     */
// todo: test listcustomers
    @Test
    @Disabled
    void listCustomers() {
    }

    /**
     * Gets by email.
     *
     * @throws Exception the exception
     */
    @Test
    void getByEmail() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(get("/api/customers/testMe@gmail.com")).andExpect(status().isOk());
    }

    /**
     * Gets by product name throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void getByEmailThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((get("/api/customers/testMe@gmail.com"))).andExpect(status().isNotFound());
    }

    /**
     * Add to cart.
     *
     * @throws Exception the exception
     */
    @Test
    void addToCart() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        mockMvc.perform(post("/api/customers/testMe@gmail.com/cart/add/Test Product")).andExpect(status().isCreated());
    }

    /**
     * Add to cart throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void addToCartThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((post("/api/customers/testMe@gmail.com/cart/add/Test Product")))
                .andExpect(status().isNotFound());
    }

    /**
     * Add to cart throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void addToCartThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        mockMvc.perform((post("/api/customers/testMe@gmail.com/cart/add/Test Product")))
                .andExpect(status().isNotFound());
    }

    /**
     * Remove from cart.
     *
     * @throws Exception the exception
     */
    @Test
    void removeFromCart() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        mockMvc.perform(delete("/api/customers/testMe@gmail.com/cart/remove/Test Product")).andExpect(status().isOk());
    }

    /**
     * Remove from cart throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void removeFromCartThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((delete("/api/customers/testMe@gmail.com/cart/remove/Test Product")))
                .andExpect(status().isNotFound());
    }

    /**
     * Remove from cart throws item not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void removeFromCartThrowsItemNotFoundException() throws Exception {
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        mockMvc.perform((delete("/api/customers/testMe@gmail.com/cart/remove/Test Product")))
                .andExpect(status().isNotFound());
    }

    /**
     * Empty cart.
     *
     * @throws Exception the exception
     */
    @Test
    void emptyCart() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(patch("/api/customers/testMe@gmail.com/cart/empty")).andExpect(status().isOk());
    }

    /**
     * Empty cart throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void emptyCartThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((patch("/api/customers/testMe@gmail.com/cart/empty"))).andExpect(status().isNotFound());
    }

    /**
     * Gets cart.
     *
     * @throws Exception the exception
     */
    @Test
    void getCart() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(get("/api/customers/testMe@gmail.com/cart/get")).andExpect(status().isOk());
    }

    /**
     * Gets cart throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void getCartThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((get("/api/customers/testMe@gmail.com/cart/get"))).andExpect(status().isNotFound());
    }

    /**
     * Add credit card.
     *
     * @throws Exception the exception
     */
    @Test
    void addCreditCard() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(post("/api/customers/testMe@gmail.com/creditCard/add").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(creditCard))).andExpect(status().isCreated());
    }

    /**
     * Add credit card throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void addCreditCardThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((post("/api/customers/testMe@gmail.com/creditCard/add").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(creditCard)))).andExpect(status().isNotFound());
    }

    /**
     * Remove credit card.
     *
     * @throws Exception the exception
     */
    @Test
    void removeCreditCard() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        mockMvc.perform(delete("/api/customers/testMe@gmail.com/creditCard/remove/4353")).andExpect(status().isOk());
    }

    /**
     * Remove credit card throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void removeCreditCardThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((delete("/api/customers/testMe@gmail.com/creditCard/remove/4353")))
                .andExpect(status().isNotFound());
    }

    /**
     * List credit card.
     *
     * @throws Exception the exception
     */
    @Test
    void listCreditCard() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(get("/api/customers/testMe@gmail.com/creditCards/list")).andExpect(status().isOk());
    }

    /**
     * List credit card throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void listCreditCardThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((get("/api/customers/testMe@gmail.com/creditCard/list"))).andExpect(status().isNotFound());
    }

    /**
     * Add order.
     *
     * @throws Exception the exception
     */
    @Test
    void addOrder() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(post("/api/customers/testMe@gmail.com/orderDetails/add").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(orderDetails))).andExpect(status().isCreated());
    }

    /**
     * Add order throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void addOrderThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((post("/api/customers/testMe@gmail.com/orderDetails/add")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(orderDetails))))
                .andExpect(status().isNotFound());
    }

    /**
     * Update order.
     *
     * @throws Exception the exception
     */
    @Test
    @Disabled
    void updateOrder() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(put("/api/customers/testMe@gmail.com/orderDetails/updateStatus/"));
    }

    /**
     * List orders.
     *
     * @throws Exception the exception
     */
    @Test
    void listOrders() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        mockMvc.perform(get("/api/customers/testMe@gmail.com/orderDetails/list")).andExpect(status().isOk());
    }

    /**
     * List orders throws customer not found exception.
     *
     * @throws Exception the exception
     */
    @Test
    void listOrdersThrowsCustomerNotFoundException() throws Exception {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        mockMvc.perform((get("/api/customers/testMe@gmail.com/orderDetails/list"))).andExpect(status().isNotFound());
    }
}
