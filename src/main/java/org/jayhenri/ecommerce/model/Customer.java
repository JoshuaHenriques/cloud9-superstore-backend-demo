package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {// TODO: nullable: false for most fields
    // @Embeddable
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private UUID uuid;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = true, length = 20)
    private String middleName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column
    private Address address;

//    @Column(nullable = false)
//    private String streetName;
//
//    @Column(nullable = false)
//    private Long streetNumber;
//
//    @Column(nullable = false)
//    private Long unitNumber;
//
//    @Column(nullable = false)
//    private String city;
//
//    @Column(nullable = false)
//    private String postalCode;
//
//    @Column(nullable = false)
//    private String province;

    public Customer(UUID uuid, String firstName, String middleName, String lastName, String phoneNumber, Address address) {//, String streetName, Long streetNumber, Long unitNumber, String city, String postalCode, String province) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
//        this.streetName = streetName;
//        this.streetNumber = streetNumber;
//        this.unitNumber = unitNumber;
//        this.city = city;
//        this.postalCode = postalCode;
//        this.province = province;
    }
}
