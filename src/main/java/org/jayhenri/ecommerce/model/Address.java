package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Address.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cus_address")
public class Address implements Serializable {

    private static final long serialVersionUID = -3706717403046249323L;

    @Id
    @Column(name = "address_id", unique = true, nullable = false)
    private UUID addressUUID = UUID.randomUUID();

    @Column(name = "customer_id", unique = true)
    private UUID customerUUID;

    @Column(name = "street_name", nullable = false, length = 25)
    private String streetName;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "unit_number", nullable = false)
    private String unitNumber;

    @Column(name = "city", nullable = false, length = 25)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 7)
    private String postalCode;

    @Column(name = "province", nullable = false, length = 25)
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
