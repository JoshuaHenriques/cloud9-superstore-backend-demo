package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Credit card.
 */
// TODO: Implement later
@Getter
@Setter
@NoArgsConstructor
@Entity
public class CreditCard implements Serializable {

    private static final long serialVersionUID = -2537205539493070885L;

    @Id
    @Column(nullable = false)
    private UUID creditCardUUID = UUID.randomUUID();

    @JoinColumn(name = "customerUUID", nullable = false, insertable = false, updatable = false)
    @ManyToOne
    private Customer customer;

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

    /**
     * Instantiates a new Credit card.
     *
     * @param fullName the full name
     * @param ccn      the ccn
     * @param expDate  the exp date
     * @param cvc      the cvc
     * @param fourDig  the four dig
     */
    public CreditCard(String fullName, String ccn, String expDate, String cvc, String fourDig) {
        this.fullName = fullName;
        this.ccn = ccn;
        this.expDate = expDate;
        this.cvc = cvc;
        this.fourDig = fourDig;
    }
}
