package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * The type Address.
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = -3706717403046249323L;

    @NotNull
    @Size(max = 100)
    @Column(nullable = false)
    private String streetName;

    @NotNull
    @Size(max = 100)
    @Column
    private String streetNumber;

    @NotNull
    @Column
    private String unitNumber;

    @NotNull
    @Column
    private String city;

    @NotNull
    @Column
    private String postalCode;

    @NotNull
    @Column
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
