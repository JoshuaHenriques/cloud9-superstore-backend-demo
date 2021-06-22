package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

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

    public CreditCard(UUID uuid, String fullName, String ccn, String expDate, Long cvc) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.ccn = ccn;
        this.expDate = expDate;
        this.cvc = cvc;
    }
}
