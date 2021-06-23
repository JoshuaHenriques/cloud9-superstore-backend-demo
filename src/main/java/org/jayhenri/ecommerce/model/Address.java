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
@NoArgsConstructor
@Entity
@Table(name = "addresses")
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

    private static final String REGEX_POSTAL_CODE = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

    public Address(UUID uuid, String streetName, Long streetNumber, Long unitNumber, String city, String postalCode, String province) throws InvalidNameException {
        this.uuid = uuid;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.unitNumber = unitNumber;
        this.city = city;
        this.province = province;

        if (isValidPostalCode(postalCode)) this.postalCode = postalCode;
        else throw new InvalidNameException();
    }

    public boolean isValidPostalCode(String postalCode) {
        Pattern pattern = Pattern.compile(REGEX_POSTAL_CODE);
        Matcher matcher = pattern.matcher(postalCode);
        return matcher.matches();
    }
}
