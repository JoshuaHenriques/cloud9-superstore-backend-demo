package org.jayhenri.ecommerce.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.CreditCardValidator;

import org.jayhenri.ecommerce.exception.InvalidCreditCardException;
import org.jayhenri.ecommerce.model.CreditCard;

import org.jayhenri.ecommerce.model.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO: Update Customer through CustomerService methods
@Getter
@Setter
@NoArgsConstructor
@Service
public class CreditCardService {

    private CreditCard creditCard;

    public void CreditCard() {
        creditCard = new CreditCard();
    }

    public void validate(CreditCard creditCard) throws InvalidCreditCardException {
        if (isValidCreditCard(creditCard.getCcn())) {
            // creditCard.setUuid(uuid);
            // creditCardRepository.save(creditCard);
        } else throw new InvalidCreditCardException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }

    public void saveToCustomer(Customer customer) {
        customer.getCreditCard().add(this.creditCard);
    }
}
