package org.jayhenri.ecommerce.service;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.jayhenri.ecommerce.exception.InvaildCreditCardException;
import org.jayhenri.ecommerce.model.CreditCard;
import org.jayhenri.ecommerce.model.Customer;
import org.jayhenri.ecommerce.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditCardService {

    @Autowired
    CreditCardRepository creditCardRepository;
    
    public CreditCardService()  {}

    public void addCreditCard(UUID uuid, CreditCard creditCard) throws InvaildCreditCardException {
        if (isValidCreditCard(creditCard.getCcn())) {
            creditCard.setUuid(uuid);
            creditCardRepository.save(creditCard);
        } else throw new InvaildCreditCardException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }
}
