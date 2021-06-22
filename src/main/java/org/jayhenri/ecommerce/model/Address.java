package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    private UUID uuid;

    @NotNull
    @Column(nullable = false)
    private String streetName;

    @NotNull
    @Column(nullable = false)
    private Long streetNumber;

    @NotNull
    @Column(nullable = false)
    private Long unitNumber;

    @NotNull
    @Column(nullable = false)
    private String city;

    @NotNull
    @Column(nullable = false)
    private String postalCode;

    @NotNull
    @Column(nullable = false)
    private String province;

    public Address(UUID uuid, String streetName, Long streetNumber, Long unitNumber, String city, String postalCode, String province) {
        this.uuid = uuid;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.province = province;
    }
}
