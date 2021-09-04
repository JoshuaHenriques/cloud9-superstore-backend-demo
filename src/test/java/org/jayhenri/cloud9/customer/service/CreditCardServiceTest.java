package org.jayhenri.cloud9.customer.service;

import org.jayhenri.store_manager.interfaces.service.customer.CreditCardServiceI;
import org.jayhenri.store_manager.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.store_manager.model.customer.Address;
import org.jayhenri.store_manager.model.customer.CreditCard;
import org.jayhenri.store_manager.model.customer.Customer;
import org.jayhenri.store_manager.model.login.Login;
import org.jayhenri.store_manager.service.customer.CreditCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * The type Credit card service test.
 */
@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {

    @Mock
    private CustomerServiceI customerService;

    private CreditCardServiceI creditCardService;

    private Customer customer;

    private CreditCard creditCard1;

    private CreditCard creditCard2;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        creditCardService = new CreditCardService(customerService);

        creditCard1 = new CreditCard(
                "John Doe",
                "5215127929840370",
                "0370",
                "363",
                "01/01/2025"
        );

        creditCard2 = new CreditCard(
                "Jessy Kurtlin",
                "5215127929842290",
                "2290",
                "132",
                "01/01/2025"
        );

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                new Address(),
                new HashSet<>(),
                new HashSet<>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );
    }

    /**
     * Add credit card.
     */
    @Test
    void addCreditCard() {

        creditCardService.add(customer, creditCard1);

        assertThat(customer.getCreditCards().contains(creditCard1)).isTrue();

        assertThat(customer.getCreditCards().size()).isEqualTo(1);
    }

    /**
     * Remove credit card.
     */
    @Test
    void removeCreditCard() {

        customer.getCreditCards().add(creditCard1);
        customer.getCreditCards().add(creditCard2);

        creditCardService.remove(customer, creditCard1.getCreditCardUUID());

        assertThat(customer.getCreditCards().contains(creditCard1)).isFalse();
        assertThat(customer.getCreditCards().size()).isEqualTo(1);
    }

    /**
     * Find all credit cards.
     */
    @Test
    void findAllCreditCards() {

        customer.getCreditCards().add(creditCard1);
        customer.getCreditCards().add(creditCard2);

        assertThat(creditCardService.findAll(customer)).isEqualTo(customer.getCreditCards());
    }

    /**
     * Exists by id.
     */
    @Test
    void existsById() {

        customer.getCreditCards().add(creditCard1);

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.existsById(customer, uuid)).isTrue();
    }

    /**
     * Does not exists by id.
     */
    @Test
    void doesNotExistsById() {

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.existsById(customer, uuid)).isFalse();
    }

    /**
     * Gets by id.
     */
    @Test
    void getById() {

        customer.getCreditCards().add(creditCard1);

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.getById(customer, uuid)).isEqualTo(creditCard1);
    }

    /**
     * Exists by ccn.
     */
    @Test
    void existsByCCN() {

        given(customerService.existsByCCN("5215127929842290")).willReturn(true);

        assertThat(creditCardService.existsByCCN("5215127929842290")).isTrue();
    }

    /**
     * Does not exists by ccn.
     */
    @Test
    void doesNotExistsByCCN() {

        given(customerService.existsByCCN("5215127929842290")).willReturn(false);

        assertThat(creditCardService.existsByCCN("5215127929842290")).isFalse();
    }
}