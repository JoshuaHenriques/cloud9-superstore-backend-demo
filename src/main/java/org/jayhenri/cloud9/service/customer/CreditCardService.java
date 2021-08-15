package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The type Credit card service.
 */
@Service
public class CreditCardService {

    private final CustomerService customerService;

    /**
     * Instantiates a new Credit card service.
     *
     * @param customerService the customer service
     */
    @Autowired
    public CreditCardService(CustomerService customerService) {

        this.customerService = customerService;
    }

    /**
     * Add credit card.
     *
     * @param customer   the customer
     * @param creditCard the credit card
     * @return the customer
     */
    public void addCreditCard(Customer customer, CreditCard creditCard) {

        customer.getWallet().add(creditCard);
        customerService.update(customer);
    }

    /**
     * Remove credit card.
     *
     * @param customer the customer
     * @param cardId   the card id
     * @return the customer
     */
    public void removeCreditCard(Customer customer, UUID cardId) {

        Set<CreditCard> removeMe = new HashSet<>();
        CreditCard card = getById(customer, cardId);
        removeMe.add(card);
        customer.getWallet().removeAll(removeMe);

        customerService.update(customer);
    }

    /**
     * Find all credit cards list.
     *
     * @param customer the email
     * @return the list
     */
    public Set<CreditCard> findAllCreditCards(Customer customer) {

        return customer.getWallet();
    }

    /**
     * Exists by email boolean.
     *
     * @param customer the customer
     * @param cardId   the card id
     * @return the boolean
     */
    public boolean existsById(Customer customer, UUID cardId) {

        AtomicBoolean exists = new AtomicBoolean(false);
        customer.getWallet().forEach(card -> {
            if (card.getCreditCardUUID().equals(cardId)) {
                exists.set(true);
            }
        });
        return exists.get();
    }

    /**
     * Gets by email.
     *
     * @param customer the customer
     * @param cardId   the card id
     * @return the by email
     */
    public CreditCard getById(Customer customer, UUID cardId) {

        AtomicReference<CreditCard> creditCard = new AtomicReference<>(new CreditCard());
        customer.getWallet().forEach(card -> {
            if (card.getCreditCardUUID().equals(cardId)) {
                creditCard.set(card);
            }
        });
        return creditCard.get();
    }

    /**
     * Exists by email boolean.
     *
     * @param ccn the credit card number
     * @return the boolean
     */
    public boolean existsByCCN(String ccn) {

        return customerService.existsByCCN(ccn);
    }
}
