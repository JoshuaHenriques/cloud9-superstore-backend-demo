package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.InvalidNameException;
import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class Address {

    @Id
    private UUID uuid;

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

    public Address(UUID uuid, String streetName, Long streetNumber, Long unitNumber, String city, String postalCode, String province) throws InvalidNameException {
        this.uuid = uuid;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;        
    }
}
