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
    private String phoneNumber;

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

    public Customer(UUID id, String firstName, String middleName, String lastName, String email, String phoneNumber, Address address, Orders orders, ArrayList<CreditCard> creditCards, Cart cart, LoginInformation login) throws InvalidNameException{
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orders = orders;
        this.creditCards = creditCards;
        this.cart = cart;
        this.login = login;

        if (isValidEmail(email)) this.email = email;
        else throw new InvalidNameException();
    }

    public boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
