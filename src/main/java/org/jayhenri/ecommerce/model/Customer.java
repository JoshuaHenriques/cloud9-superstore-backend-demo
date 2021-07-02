package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

// TODO: nullable: false for most field
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = -2054386655979281969L;

    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_NONADMIN = 0;

    @Id
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false, length = 128)
    private String password;

    @Column(nullable = false, length = 6)
    private String dateOfBirth;

    @Column(nullable = true)
    private Address address;

    private Cart cart;

    private CreditCard creditCard;

    private Order orders;

    public Customer(String firstName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address) {
        this.firstName = firstName;
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
