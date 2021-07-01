package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    @Column(nullable = false)
    private String streetName;

    @Column(nullable = false)
    private Long streetNumber;

    @Column(nullable = false)
    private Long unitNumber;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String province;

    public Address(String streetName, Long streetNumber, Long unitNumber, String city, String postalCode, String province) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
    }
}
