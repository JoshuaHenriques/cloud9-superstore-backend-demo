package org.jayhenri.cloud9.customer.controller;

import org.jayhenri.cloud9.controller.customer.CreditCardController;
import org.jayhenri.cloud9.exception.alreadyexists.CreditCardAlreadyExistsException;
import org.jayhenri.cloud9.exception.invalid.InvalidCreditCardException;
import org.jayhenri.cloud9.exception.invalid.InvalidOrdersException;
import org.jayhenri.cloud9.exception.notfound.CreditCardNotFoundException;
import org.jayhenri.cloud9.exception.notfound.CustomerNotFoundException;
import org.jayhenri.cloud9.interfaces.controller.customer.CreditCardControllerI;
import org.jayhenri.cloud9.interfaces.service.customer.CreditCardServiceI;
import org.jayhenri.cloud9.interfaces.service.customer.CustomerServiceI;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.customer.Cart;
import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.model.customer.Customer;
import org.jayhenri.cloud9.model.login.Login;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

/**
 * The type Credit card controller test.
 */
@ExtendWith(MockitoExtension.class)
public class CreditCardControllerTest {

    private CreditCardControllerI creditCardController;

    @Captor
    private ArgumentCaptor<CreditCard> captorCreditCard;

    @Captor
    private ArgumentCaptor<Customer> captorCustomer;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @Mock
    private CustomerServiceI customerService;

    @Mock
    private CreditCardServiceI creditCardService;

    private Customer customer;

    private UUID uuid;

    private UUID uuid1;

    private CreditCard creditCard1;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {

        uuid = UUID.randomUUID();
        uuid1 = UUID.randomUUID();

        creditCardController = new CreditCardController(customerService, creditCardService);

        creditCard1 = new CreditCard(
                "John Doe",
                "5215127929840370",
                "0370",
                "363",
                "01/01/2025"
        );

        customer = new Customer(
                "customer.mail@gmail.com",
                new Login(),
                new Cart(),
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
     *
     * @throws CreditCardAlreadyExistsException the credit card already exists exception
     * @throws InvalidOrdersException           the invalid orders exception
     * @throws CustomerNotFoundException        the customer not found exception
     * @throws InvalidCreditCardException       the invalid credit card exception
     */
    @Test
    void addCreditCard() throws CreditCardAlreadyExistsException, InvalidOrdersException, CustomerNotFoundException, InvalidCreditCardException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);
        given(customerService.existsByCCN("5215127929840370")).willReturn(true);

        ResponseEntity<String> response = creditCardController.add(uuid, creditCard1);

        then(creditCardService).should().add(captorCustomer.capture(), captorCreditCard.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorCreditCard.getValue()).isEqualTo(creditCard1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    /**
     * Add credit card throws already exists exception.
     */
    @Test
    void addCreditCardThrowsAlreadyExistsException() {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.existsByCCN("5215127929840370")).willReturn(false);

        assertThrows(CreditCardAlreadyExistsException.class, () -> creditCardController.add(uuid, creditCard1));
    }

    /**
     * Add credit card throws customer not found exception.
     */
    @Test
    void addCreditCardThrowsCustomerNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> creditCardController.add(uuid, creditCard1));
    }

    /**
     * Add credit card throws invalid credit card exception.
     */
    @Test
    void addCreditCardThrowsInvalidCreditCardException() {

        assertThrows(InvalidCreditCardException.class, () -> creditCardController.add(null, null));
    }

    /**
     * Remove credit card.
     *
     * @throws CustomerNotFoundException   the customer not found exception
     * @throws CreditCardNotFoundException the credit card not found exception
     */
    @Test
    void removeCreditCard() throws CustomerNotFoundException, CreditCardNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);
        given(creditCardService.existsById(customer, uuid1)).willReturn(true);

        ResponseEntity<String> response = creditCardController.delete(uuid, uuid1);

        then(creditCardService).should().remove(captorCustomer.capture(), captorUUID.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(captorUUID.getValue()).isEqualTo(uuid1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * Remove credit card throws credit card not found exception.
     */
    @Test
    void removeCreditCardThrowsCreditCardNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);
        given(creditCardService.existsById(customer, uuid1)).willReturn(false);

        assertThrows(CreditCardNotFoundException.class, () -> creditCardController.delete(uuid, uuid1));
    }

    /**
     * Remove credit card throws customer not found exception.
     */
    @Test
    void removeCreditCardThrowsCustomerNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> creditCardController.delete(uuid, uuid1));
    }

    /**
     * List credit cards.
     *
     * @throws CustomerNotFoundException the customer not found exception
     */
    @Test
    void listCreditCards() throws CustomerNotFoundException {

        given(customerService.existsById(uuid)).willReturn(true);
        given(customerService.getById(uuid)).willReturn(customer);

        ResponseEntity<Set<CreditCard>> response = creditCardController.list(uuid);

        then(creditCardService).should().findAll(captorCustomer.capture());

        assertThat(captorCustomer.getValue()).isEqualTo(customer);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    /**
     * List credit cards throws customer not found exception.
     */
    @Test
    void listCreditCardsThrowsCustomerNotFoundException() {

        given(customerService.existsById(uuid)).willReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> creditCardController.list(uuid));
    }
}
