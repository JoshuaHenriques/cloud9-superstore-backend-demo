package org.jayhenri.cloud9.model.customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Credit card.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard implements Serializable {

    private static final long serialVersionUID = -2537205539493070885L;

    @Id
    @Column(name = "credit_card_id", nullable = false)
    private UUID creditCardUUID = UUID.randomUUID();

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "ccn", unique = true, nullable = false, length = 16)
    private String ccn;

    @Column(name = "four_dig", unique = true, nullable = false, length = 4)
    private String fourDig;

    @Column(name = "cvc", nullable = false, length = 3)
    private String cvc;

    @Column(name = "exp_date", nullable = false, length = 10)
    private String expDate;

    /**
     * Instantiates a new Credit card.
     *
     * @param fullName the full name
     * @param ccn      the ccn
     * @param fourDig  the four dig
     * @param cvc      the cvc
     * @param expDate  the exp date
     */
    public CreditCard(String fullName, String ccn, String fourDig, String cvc, String expDate) {
        this.fullName = fullName;
        this.ccn = ccn;
        this.fourDig = fourDig;
        this.cvc = cvc;
        this.expDate = expDate;
    }
}
