package org.jayhenri.ecommerce.service;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.jayhenri.ecommerce.exception.CreditCardException;
import org.jayhenri.ecommerce.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditCardService {
    
    @Autowired
    CreditCard creditCard;
    
    public CreditCardService(UUID uuid, String fullName, String ccn, String expDate, Long cvc) throws CreditCardException {
        if (isValidCreditCard(ccn)) creditCard = new CreditCard(uuid, fullName, ccn, expDate, cvc);
        else throw new CreditCardException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }
}
