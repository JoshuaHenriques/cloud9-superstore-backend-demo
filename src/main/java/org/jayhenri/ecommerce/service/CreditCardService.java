package org.jayhenri.ecommerce.service;

import org.apache.commons.validator.routines.CreditCardValidator;
import org.jayhenri.ecommerce.exception.InvaildCreditCardException;
import org.jayhenri.ecommerce.model.CreditCard;
import org.jayhenri.ecommerce.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreditCardService {

    
    public CreditCardService()  {}

    public void addCreditCard(Customer customer, CreditCard creditCard) throws InvaildCreditCardException {
        if (isValidCreditCard(creditCard.getCcn())) customer.setCreditCard(creditCard);
        else throw new InvaildCreditCardException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }
}
