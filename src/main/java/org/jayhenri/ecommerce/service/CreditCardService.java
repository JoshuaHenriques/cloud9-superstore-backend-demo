package org.jayhenri.ecommerce.service;

import org.apache.commons.validator.routines.CreditCardValidator;

import org.jayhenri.ecommerce.exception.InvalidCreditCardException;
import org.jayhenri.ecommerce.model.CreditCard;

import org.springframework.stereotype.Service;

import java.util.UUID;

// TODO: Update Customer through CustomerService methods
@Service
public class CreditCardService {
    
    public CreditCardService()  {}

    public void addCreditCard(UUID uuid, CreditCard creditCard) throws InvalidCreditCardException {
        if (isValidCreditCard(creditCard.getCcn())) {
            // creditCard.setUuid(uuid);
            // creditCardRepository.save(creditCard);
        } else throw new InvalidCreditCardException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }

    public void saveToCustomer() {}
}
