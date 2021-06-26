package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.validator.routines.EmailValidator;

import javax.naming.InvalidNameException;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {// TODO: nullable: false for most fields

    @Id
    private UUID id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = true, length = 20)
    private String middleName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    private Orders orders;

    @Column(nullable = false)
    private CreditCard creditCard;

    @Column(nullable = false)
    private Cart cart;

//    @Column(nullable = false)
//    private Login login;

    public Customer(UUID id, String firstName, String middleName, String lastName, String email, String phoneNumber, Address address, Orders orders, CreditCard creditCard, Cart cart, Login login) throws InvalidNameException{
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orders = orders;
        this.creditCard = creditCard;
        this.cart = cart;
        // this.login = login;
    }



}
