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
public class Customer {// TODO: nullable: false for most field

    @Id
    private UUID uuid;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = true, length = 20)
    private String middleName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 6)
    private String dateOfBirth;

    @Column(nullable = true)
    private Address address;

    @Column(nullable = false)
    private Cart cart;

    @Column(nullable = true)
    private CreditCard creditCard;

    @Column(nullable = true)
    private Order orders;

    public Customer(UUID uuid, String firstName, String middleName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.cart = new Cart();
        this.creditCard = new CreditCard();
        this.orders = new Order();
    }
}
