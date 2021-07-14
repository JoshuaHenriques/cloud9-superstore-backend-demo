package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * The type Address.
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = -3706717403046249323L;
    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private String streetNumber;

    @Column(nullable = false)
    private String unitNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String province;

    /**
     * Instantiates a new Address.
     *
     * @param streetName   the street name
     * @param streetNumber the street number
     * @param unitNumber   the unit number
     * @param city         the city
     * @param postalCode   the postal code
     * @param province     the province
     */
    public Address(String streetName, String streetNumber, String unitNumber, String city, String postalCode, String province) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
    }
}
