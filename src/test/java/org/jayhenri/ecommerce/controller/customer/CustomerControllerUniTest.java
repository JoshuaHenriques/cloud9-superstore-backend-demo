package org.jayhenri.ecommerce.controller.customer;

import org.jayhenri.ecommerce.controller.CustomerController;
import org.jayhenri.ecommerce.exception.*;
import org.jayhenri.ecommerce.model.*;
import org.jayhenri.ecommerce.service.CustomerService;
import org.jayhenri.ecommerce.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Customer controller uni test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerControllerUniTest {

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Captor
    private ArgumentCaptor<Item> captorItem;

    @Captor
    private ArgumentCaptor<CreditCard> captorCreditCard;

    @Captor
    private ArgumentCaptor<OrderDetails> captorOrderDetails;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Mock
    private CustomerService customerService;

    @Mock
    private InventoryService inventoryService;

    private CustomerController testMe;

    private Customer customer;

    private Item item;

    private Inventory inventory;

    private CreditCard creditCard;

    private UUID uuid;

    private OrderDetails orderDetails1;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        testMe = new CustomerController(customerService, inventoryService);
        inventory = new Inventory();

        ArrayList<Item> items = new ArrayList<>();
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        ArrayList<CreditCard> creditCards = new ArrayList<>();

        item = new Item(
                "Test Product",
                "Test Description",
                9.99
        );

        items.add(item);

        inventory = new Inventory(
                "Test Product",
                329,
                item
        );

        orderDetails1 = new OrderDetails(
                "PROCESSING",
                "testMe@gmail.com",
                items,
                9.00
        );

        Cart cart = new Cart(
                items,
                "testMe@gmail.com",
                29.23
        );

        creditCard = new CreditCard(
                "Ubuntu User",
                "4716902620158281",
                "8281",
                "05/25",
                "8281"
        );

        orderDetails.add(orderDetails1);
        // creditCards.add(creditCard);

        customer = new Customer(
                "testMe",
                "TestMe",
                "2934811932",
                "testMe@gmail.com",
                "testMePassword",
                "082395",
                new Address(
                        "Test Me",
                        "29L",
                        "0L",
                        "New York",
                        "T2K9R3",
                        "Province"
                ),
                cart,
                creditCards,
                orderDetails
        );
    }

    /**
     * Update customer.
     *
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void updateCustomer() throws InvalidCustomerException, CustomerNotFoundException {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(true);

        testMe.updateCustomer(customer);

        then(customerService).should().update(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Update customer throws invalid customer exception.
     */
    @Test
    void updateCustomerThrowsInvalidCustomerException() {
        assertThrows(InvalidCustomerException.class, () -> {
            testMe.updateCustomer(null);
        });
    }

    /**
     * Update customer throws customer already exists exception.
     */
    @Test
    void updateCustomerThrowsCustomerAlreadyExistsException() {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.updateCustomer(customer);
        });
    }

    /**
     * Delete customer.
     *
     * @throws InvalidCustomerException  the invalid customer exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void deleteCustomer() throws InvalidCustomerException, CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.deleteCustomer("testMe@gmail.com");

        then(customerService).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCustomer.getValue().getEmail()).isEqualTo("testMe@gmail.com");
    }

    /**
     * Delete customer throws customer not found exception.
     */
    @Test
    void deleteCustomerThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.deleteCustomer("testMe@gmail.com");
        });
    }

    /**
     * Delete customer throws invalid customer exception.
     */
    @Test
    void deleteCustomerThrowsInvalidCustomerException() {
        assertThrows(InvalidCustomerException.class, () -> {
            testMe.deleteCustomer(null);
        });
    }

    /**
     * List customers.
     */
    @Test
    @Disabled
    void listCustomers() {

    }

    /**
     * Gets by email.
     *
     * @throws InvalidNameException      the invalid name exception
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void getByEmail() throws InvalidNameException, CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        testMe.getByEmail("testMe@gmail.com");

        then(customerService).should().getByEmail(captorString.capture());

        assertThat("testMe@gmail.com").isEqualTo(captorString.getValue());
    }

    /**
     * Gets by email throws invalid name exception.
     */
    @Test
    void getByEmailThrowsInvalidNameException() {
        assertThrows(InvalidNameException.class, () -> {
            testMe.getByEmail(null);
        });
    }

    /**
     * Gets by email throws customer not found exception.
     */
    @Test
    void getByEmailThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.getByEmail("testMe@gmail.com");
        });
    }

    /**
     * Add to cart.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @Test
    void addToCart() throws CustomerNotFoundException, ItemNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);

        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        testMe.addToCart("Test Product", "testMe@gmail.com");

        then(customerService).should().addToCart(captorCustomer.capture(), captorItem.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(inventory.getItem());
    }

    /**
     * Add to cart throws customer not found exception.
     */
    @Test
    void addToCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.addToCart("Test Product", "testMe@gmail.com");
        });
    }

    /**
     * Add to cart throws item not found exception.
     */
    @Test
    void addToCartThrowsItemNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.addToCart("Test Product", "testMe@gmail.com");
        });
    }

    /**
     * Remove from cart.
     *
     * @throws CustomerNotFoundException the customer not found exception
     * @throws ItemNotFoundException     the item not found exception
     */
    @Test
    void removeFromCart() throws CustomerNotFoundException, ItemNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);

        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.removeFromCart("Test Product", "testMe@gmail.com");

        then(customerService).should().removeFromCart(captorCustomer.capture(), captorString.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorString.getValue()).isEqualTo("Test Product");
    }

    /**
     * Remove from cart throws customer not found exception.
     */
    @Test
    void removeFromCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.removeFromCart("Test Product", "testMe@gmail.com");
        });
    }

    /**
     * Remove from cart throws item not found exception.
     */
    @Test
    void removeFromCartThrowsItemNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.removeFromCart("Test Product", "testMe@gmail.com");
        });
    }

    /**
     * Empty cart.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void emptyCart() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.emptyCart("testMe@gmail.com");

        then(customerService).should().emptyCart(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Empty cart throws customer not found exception.
     */
    @Test
    void emptyCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.emptyCart("testMe@gmail.com");
        });
    }

    /**
     * Gets cart.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void getCart() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.getCart("testMe@gmail.com");

        then(customerService).should().getCart(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Gets cart throws customer not found exception.
     */
    @Test
    void getCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.getCart("testMe@gmail.com");
        });
    }

    /**
     * Add credit card.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void addCreditCard() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.addCreditCard("testMe@gmail.com", creditCard);

        then(customerService).should().addCreditCard(captorCustomer.capture(), captorCreditCard.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCreditCard.getValue()).isEqualTo(creditCard);
    }

    /**
     * Add credit card throws customer not found exception.
     */
    @Test
    void addCreditCardThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
           testMe.addCreditCard("testMe@gmail.com", creditCard);
        });
    }

    /**
     * Remove credit card.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void removeCreditCard() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.removeCreditCard("testMe@gmail.com", "4352");

        then(customerService).should().removeCreditCard(captorCustomer.capture(), captorString.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorString.getValue()).isEqualTo("4352");
    }

    /**
     * Remove credit card throws customer not found exception.
     */
    @Test
    void removeCreditCardThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.removeCreditCard("testMe@gmail.com", "4352");
        });
    }

    /**
     * List credit cards.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void listCreditCards() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.listCreditCards("testMe@gmail.com");

        then(customerService).should().findAllCreditCards(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * List credit cards throws customer not found exception.
     */
    @Test
    void listCreditCardsThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
           testMe.listCreditCards("testMe@gmail.com");
        });
    }

    /**
     * Add order.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void addOrder() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.addOrder("testMe@gmail.com", orderDetails1);

        then(customerService).should().addOrder(captorCustomer.capture(), captorOrderDetails.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorOrderDetails.getValue()).isEqualTo(orderDetails1);
    }

    /**
     * Add order throws customer not found exception.
     */
    @Test
    void addOrderThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
           testMe.addOrder("testMe@gmail.com", orderDetails1);
        });
    }

    /**
     * Update order.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void updateOrder() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.updateOrder("testMe@gmail.com", uuid, "TEST");

        then(customerService).should().updateOrder(captorCustomer.capture(), captorUUID.capture(), captorString.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
        assertThat(captorString.getValue()).isEqualTo("TEST");
    }

    /**
     * Update order throws customer not found exception.
     */
    @Test
    void updateOrderThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.updateOrder("testMe@gmail.com", uuid, "TEST");
        });
    }

    /**
     * List orders.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void listOrders() throws CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.listOrders("testMe@gmail.com");

        then(customerService).should().findAllOrders(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * List orders throws customer not found exception.
     */
    @Test
    void listOrdersThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.listOrders("testMe@gmail.com");
        });
    }
}
