package org.jayhenri.cloud9.service.customer;

import org.jayhenri.cloud9.model.customer.CreditCard;
import org.jayhenri.cloud9.model.customer.Customer;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Credit card service.
 */
public class CreditCardService {

    /**
     * Add credit card.
     *
     * @param customer   the customer
     * @param creditCard the credit card
     */
    public Customer addCreditCard(Customer customer, CreditCard creditCard) {

        customer.getWallet().add(creditCard);
        return customer;
    }

    /**
     * Remove credit card.
     *
     * @param customer the customer
     * @param fourDig  the four dig
     */
    public Customer removeCreditCard(Customer customer, String fourDig) {

        Set<CreditCard> removeMe = new HashSet<>();
        customer.getWallet().forEach(creditCard -> {
            if (creditCard.getFourDig() != null && creditCard.getFourDig().equals(fourDig)) {
                removeMe.add(creditCard);
            }
        });
        customer.getWallet().removeAll(removeMe);
        return customer;
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
}
