package org.jayhenri.ecommerce.service;

import org.jayhenri.ecommerce.exception.CustomerAlreadyExistsException;
import org.jayhenri.ecommerce.exception.InvalidPostalCodeException;
import org.jayhenri.ecommerce.model.CreditCard;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.model.Item;
import org.jayhenri.ecommerce.model.OrderDetails;
import org.jayhenri.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Customer service test.
 */
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    /**
     * The Test me.
     */
    CustomerService testMe;

    /**
     * The Customer repository.
     */
    @Mock
    CustomerRepository customerRepository;

    /**
     * The Customer service.
     */
    @Mock
    CustomerService mockMe;

    /**
     * The Captor customer.
     */
    @Captor
    ArgumentCaptor<Customer> captorCustomer;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<String> captorString;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<Item> captorItem;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<CreditCard> captorCreditCard;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<OrderDetails> captorOrderDetails;

    /**
     * The Captor string.
     */
    @Captor
    ArgumentCaptor<UUID> captorUUID;

    /**
     * The Customer.
     */
    Customer customer;

    /**
     * The Customer.
     */
    Item item;

    /**
     * The Customer.
     */
    CreditCard creditCard;

    /**
     * The Customer.
     */
    OrderDetails orderDetails;

    /**
     * The Customer.
     */
    UUID uuid;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        testMe = new CustomerService(customerRepository);
        item = new Item(
                "Test Item",
                "Test description",
                33.54
        );
        creditCard = new CreditCard(
                "Test Name",
                "4656085451466403",
                "05/23",
                "231",
                "4353"
        );
        uuid = UUID.randomUUID();
    }

    /**
     * Exists by phone number.
     */
    @Test
    void existsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(true);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isTrue();
    }

    /**
     * Exists by phone number.
     */
    @Test
    void doesNotExistsByPhoneNumber() {
        given(testMe.existsByPhoneNumber("1234567890"))
                .willReturn(false);

        Boolean bool = testMe.existsByPhoneNumber("1234567890");
        then(customerRepository).should().existsByPhoneNumber(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo("1234567890");
        assertThat(bool).isFalse();
    }

    /**
     * Add.
     *
     * @throws InvalidPostalCodeException     the invalid postal code exception
     * @throws CustomerAlreadyExistsException the customer already exists exception
     */
    @Test
    void add() throws InvalidPostalCodeException, CustomerAlreadyExistsException {
        testMe.add(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Delete.
     */
    @Test
    void delete() {
        testMe.delete(this.customer);

        then(customerRepository).should().delete(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Update.
     */
    @Test
    void update() {
        testMe.update(this.customer);

        then(customerRepository).should().save(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(this.customer);
    }

    /**
     * Find all customers.
     */
// Do later
    @Test
    @Disabled
    void findAllCustomers() {
    }

    /**
     * Find all credit cards.
     */
    @Test
    void findAllCreditCards() {
        mockMe.findAllCreditCards(customer);

        then(mockMe).should().findAllCreditCards(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Exists by email.
     */
    @Test
    void existsByEmail() {
        String email = "testMe@gmail.com";
        given(testMe.existsByEmail(email)).willReturn(true);

        boolean bool = testMe.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isTrue();
    }

    /**
     * Exists by email.
     */
    @Test
    void doesNotExistsByEmail() {
        String email = "testMe@gmail.com";
        given(testMe.existsByEmail(email)).willReturn(false);

        boolean bool = testMe.existsByEmail(email);

        then(customerRepository).should().existsByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
        assertThat(bool).isFalse();
    }

    /**
     * Gets by email.
     */
    @Test
    void getByEmail() {
        String email = "testMe@gmail.com";
        testMe.getByEmail(email);

        then(customerRepository).should().getByEmail(captorString.capture());

        assertThat(captorString.getValue()).isEqualTo(email);
    }

    /**
     * Add to cart.
     */
    @Test
    void addToCart() {
        mockMe.addToCart(customer, item);

        then(mockMe).should().addToCart(captorCustomer.capture(), captorItem.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorItem.getValue()).isEqualTo(item);
    }

    /**
     * Remove from cart.
     */
    @Test
    @Disabled
    void removeFromCart() {
    }

    /**
     * Empty cart.
     */
    @Test
    @Disabled
    void emptyCart() {
    }

    /**
     * Gets cart.
     */
    @Test
    void getCart() {
        mockMe.getCart(customer);

        then(mockMe).should().getCart(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }

    /**
     * Add credit card.
     */
    @Test
    void addCreditCard() {
        mockMe.addCreditCard(customer, creditCard);

        then(mockMe).should().addCreditCard(captorCustomer.capture(), captorCreditCard.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCreditCard.getValue()).isEqualTo(creditCard);
    }

    /**
     * Remove credit card.
     */
    @Test
    @Disabled
    void removeCreditCard() {
    }

    /**
     * Add order.
     */
    @Test
    void addOrder() {
        mockMe.addOrder(customer, orderDetails);

        then(mockMe).should().addOrder(captorCustomer.capture(), captorOrderDetails.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorOrderDetails.getValue()).isEqualTo(orderDetails);
    }

    /**
     * Update order.
     */
    @Test
    void updateOrder() {
        mockMe.updateOrder(customer, uuid, "DELIVERED");

        then(mockMe).should().updateOrder(captorCustomer.capture(), captorUUID.capture(), captorString.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(uuid);
        assertThat(captorString.getValue()).isEqualTo("DELIVERED");
    }

    /**
     * Find all orders.
     */
    @Test
    void findAllOrders() {
        mockMe.findAllOrders(customer);

        then(mockMe).should().findAllOrders(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
    }
}