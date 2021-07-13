package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

// TODO: Implement later
@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Entity
public class CreditCard implements Serializable {

    private static final long serialVersionUID = -2537205539493070885L;
    @Column
    private String fullName;

    @Column
    private String ccn;

    @Column
    private String fourDig;

    @Column
    private String expDate;

    @Column
    private String cvc;

    public CreditCard(String fullName, String ccn, String expDate, String cvc, String fourDig) {
        this.fullName = fullName;
        this.ccn = ccn;
        this.expDate = expDate;
        this.cvc = cvc;
        this.fourDig = fourDig;
    }
}
