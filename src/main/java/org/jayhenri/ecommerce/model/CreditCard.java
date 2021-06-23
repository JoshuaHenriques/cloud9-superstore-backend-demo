package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.InvalidNameException;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

import org.apache.commons.validator.routines.CreditCardValidator;

// TODO: Implement later
public class CreditCard {

    @Id
    private UUID uuid;

    @Column
    private String fullName;

    @Column
    private String ccn;

    @Column
    private String expDate;

    @Column
    private Long cvc;

    public CreditCard(UUID uuid, String fullName, String ccn, String expDate, Long cvc) throws InvalidNameException {
        this.uuid = uuid;
        this.fullName = fullName;
        this.expDate = expDate;
        this.cvc = cvc;

        if (isValidCreditCard(ccn)) this.ccn = ccn;
        else throw new InvalidNameException();
    }

    public boolean isValidCreditCard(String ccn) {
        CreditCardValidator ccv = new CreditCardValidator(CreditCardValidator.VISA);
        return ccv.isValid(ccn);
    }
}
