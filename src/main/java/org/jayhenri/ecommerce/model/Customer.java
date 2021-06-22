package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer { // TODO: nullable: false for most fields

    @Id
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @Column(nullable = true)
    private String middleName;

    @NotNull
    @Column(nullable = false)
    private String lastName;

    @NotNull
    @Column(nullable = true)
    private Address address;

    @Column(nullable = true)
    private Orders orders;

    @Column(nullable = true)
    private ArrayList<CreditCard> creditCards;

    @Column(nullable = true)
    private Cart cart;

    @Column(nullable = true)
    private LoginInformation login;

    public Customer(UUID id, String firstName, String middleName, String lastName, Address address, Orders orders, ArrayList<CreditCard> creditCards, Cart cart, LoginInformation login) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.orders = orders;
        this.creditCards = creditCards;
        this.cart = cart;
        this.login = login;
    }
}
