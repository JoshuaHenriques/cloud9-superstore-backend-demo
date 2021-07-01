package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

// TODO: Implement later
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class CreditCard {

    @Column
    private String fullName;

    @Column
    private String ccn;

    @Column
    private String expDate;

    @Column
    private String cvc;

    public CreditCard(String fullName, String ccn, String expDate, String cvc) {
        this.fullName = fullName;
        this.expDate = expDate;
        this.cvc = cvc;
    }
}
