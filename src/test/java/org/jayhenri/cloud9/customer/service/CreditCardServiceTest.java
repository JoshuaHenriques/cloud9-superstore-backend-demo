package org.jayhenri.cloud9.customer.service;

import org.jayhenri.cloud9.model.customer.*;
import org.jayhenri.cloud9.model.login.Login;
import org.jayhenri.cloud9.service.customer.CreditCardService;
import org.jayhenri.cloud9.service.customer.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {

    @Mock
    private CustomerService customerService;

    private CreditCardService creditCardService;

    private Customer customer;

    private CreditCard creditCard1;

    private CreditCard creditCard2;

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
                new Cart(),
                new Address(),
                new HashSet<CreditCard>(),
                new HashSet<Orders>(),
                "John",
                "Doe",
                "6473829338",
                "08/23/1995"
        );
    }

    @Test
    void addCreditCard() {

        creditCardService.addCreditCard(customer, creditCard1);

        assertThat(customer.getWallet().contains(creditCard1)).isTrue();

        assertThat(customer.getWallet().size()).isEqualTo(1);
    }

    @Test
    void removeCreditCard() {

        customer.getWallet().add(creditCard1);
        customer.getWallet().add(creditCard2);

        creditCardService.removeCreditCard(customer, creditCard1.getCreditCardUUID());

        assertThat(customer.getWallet().contains(creditCard1)).isFalse();
        assertThat(customer.getWallet().size()).isEqualTo(1);
    }

    @Test
    void findAllCreditCards() {

        customer.getWallet().add(creditCard1);
        customer.getWallet().add(creditCard2);

        assertThat(creditCardService.findAllCreditCards(customer)).isEqualTo(customer.getWallet());
    }

    @Test
    void existsById() {

        customer.getWallet().add(creditCard1);

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.existsById(customer, uuid)).isTrue();
    }

    @Test
    void doesNotExistsById() {

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.existsById(customer, uuid)).isFalse();
    }

    @Test
    void getById() {

        customer.getWallet().add(creditCard1);

        UUID uuid = creditCard1.getCreditCardUUID();

        assertThat(creditCardService.getById(customer, uuid)).isEqualTo(creditCard1);
    }

    @Test
    void existsByCCN() {

        given(customerService.existsByCCN("5215127929842290")).willReturn(true);

        assertThat(creditCardService.existsByCCN("5215127929842290")).isTrue();
    }

    @Test
    void doesNotExistsByCCN() {

        given(customerService.existsByCCN("5215127929842290")).willReturn(false);

        assertThat(creditCardService.existsByCCN("5215127929842290")).isFalse();
    }
}