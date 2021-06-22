package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
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
    @Column(nullable = false)
    private String email;
    
    @NotNull
    @Column(nullable = false)
    private String phonenumber;

    @NotNull
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private Orders orders;

    @Column(nullable = false)
    private ArrayList<CreditCard> creditCards;

    @Column(nullable = false)
    private Cart cart;

    @Column(nullable = false)
    private LoginInformation login;

    public Customer(UUID id, String firstName, String middleName, String lastName, String email, String phonenumber, Address address, Orders orders, ArrayList<CreditCard> creditCards, Cart cart, LoginInformation login) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.address = address;
        this.orders = orders;
        this.creditCards = creditCards;
        this.cart = cart;
        this.login = login;
    }
}
