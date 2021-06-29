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

    @Column
    private Address address;

    @Column
    private Cart cart;

    @Column
    private CreditCard creditCard;

    @Column
    private Login login;

    @Column
    private Orders orders;

    public Customer(String firstName, String middleName, String lastName, String phoneNumber, Address address, Cart cart, CreditCard creditCard, Login login, Orders orders) {
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cart = cart;
        this.creditCard = creditCard;
        this.login = login;
        this.orders = orders;
    }
}
