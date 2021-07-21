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
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;

import javax.naming.InvalidNameException;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CustomerControllerUniTest {

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<String> captorString;

    @Mock
    private CustomerService customerService;

    @Mock
    private InventoryService inventoryService;

    private CustomerController testMe;

    private Customer customer;

    private Item item;

    private Inventory inventory;

    @BeforeEach
    void setUp() {
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

        OrderDetails orderDetails1 = new OrderDetails(
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

        CreditCard creditCard = new CreditCard(
                "Ubuntu User",
                "4716902620158281",
                "8281",
                "05/25",
                "8281"
        );

        orderDetails.add(orderDetails1);
        creditCards.add(creditCard);

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

    @Test
    void updateCustomer() throws InvalidCustomerException, CustomerNotFoundException {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(true);

        testMe.updateCustomer(customer);

        then(customerService).should().update(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    @Test
    void updateCustomerThrowsInvalidCustomerException() {
        assertThrows(InvalidCustomerException.class, () -> {
            testMe.updateCustomer(null);
        });
    }

    @Test
    void updateCustomerThrowsCustomerAlreadyExistsException() {
        given(customerService.existsByEmail(customer.getEmail())).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.updateCustomer(customer);
        });
    }

    @Test
    void deleteCustomer() throws InvalidCustomerException, CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);

        testMe.deleteCustomer("testMe@gmail.com");

        then(customerService).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCustomer.getValue().getEmail()).isEqualTo("testMe@gmail.com");
    }

    @Test
    void deleteCustomerThrowsCustomerNotFoundException() {
        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.deleteCustomer("testMe@gmail.com");
        });
    }

    @Test
    void deleteCustomerThrowsInvalidCustomerException() {
        assertThrows(InvalidCustomerException.class, () -> {
            testMe.deleteCustomer(null);
        });
    }

    @Test
    @Disabled
    void listCustomers() {

    }

    @Test
    void getByEmail() throws InvalidNameException, CustomerNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);

        testMe.getByEmail("testMe@gmail.com");

        then(customerService).should().getByEmail(captorString.capture());

        assertThat("testMe@gmail.com").isEqualTo(captorString.getValue());
    }

    @Test
    void getByEmailThrowsInvalidNameException() {
        assertThrows(InvalidNameException.class, () -> {
            testMe.getByEmail(null);
        });
    }

    @Test
    void getByEmailThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.getByEmail("testMe@gmail.com");
        });
    }

    @Test
    void addToCart() throws CustomerNotFoundException, ItemNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);

        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        testMe.addToCart("Test Product", "testMe@gmail.com");

        then(customerService).should().addToCart(customer, inventory.getItem());
    }

    @Test
    void addToCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.addToCart("Test Product", "testMe@gmail.com");
        });
    }

    @Test
    void addToCartThrowsItemNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.addToCart("Test Product", "testMe@gmail.com");
        });
    }

    @Test
    void removeFromCart() throws CustomerNotFoundException, ItemNotFoundException {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(true);

        given(customerService.getByEmail("testMe@gmail.com")).willReturn(customer);
        given(inventoryService.getByProductName("Test Product")).willReturn(inventory);

        testMe.removeFromCart("Test Product", "testMe@gmail.com");

        then(customerService).should().removeFromCart(customer, inventory.getItem());
    }

    @Test
    void removeFromCartThrowsCustomerNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> {
            testMe.removeFromCart("Test Product", "testMe@gmail.com");
        });
    }

    @Test
    void removeFromCartThrowsItemNotFoundException() {
        given(customerService.existsByEmail("testMe@gmail.com")).willReturn(true);
        given(inventoryService.existsByProductName("Test Product")).willReturn(false);

        assertThrows(ItemNotFoundException.class, () -> {
            testMe.removeFromCart("Test Product", "testMe@gmail.com");
        });
    }
}