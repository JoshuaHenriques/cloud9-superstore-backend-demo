package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.InvalidNameException;
import javax.persistence.*;
import java.util.UUID;

import org.apache.commons.validator.routines.CreditCardValidator;

// TODO: Implement later
@Getter
@Setter
@NoArgsConstructor
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

    public CreditCard(UUID uuid, String fullName, String ccn, String expDate, Long cvc) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.expDate = expDate;
        this.cvc = cvc;
    }
}
