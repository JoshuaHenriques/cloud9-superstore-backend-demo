package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The type Customer.
 */
// TODO: nullable: false for most field
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    /**
     * The constant ROLE_ADMIN.
     */
    public static final int ROLE_ADMIN = 1;
    /**
     * The constant ROLE_NONADMIN.
     */
    public static final int ROLE_NONADMIN = 0;

    private static final long serialVersionUID = -1854209356482886781L;

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

    @Embedded
    private Address address;

    @Embedded
    private Cart cart;

    @Column
    @OneToMany
    private ArrayList<CreditCard> creditCards;

    @Column
    @OneToMany
    private ArrayList<Order> orders;

    /**
     * Instantiates a new Customer.
     *
     * @param firstName   the first name
     * @param lastName    the last name
     * @param phoneNumber the phone number
     * @param email       the email
     * @param password    the password
     * @param dateOfBirth the date of birth
     * @param address     the address
     * @param cart        the cart
     * @param creditCards the credit cards
     * @param orders      the orders
     */
    public Customer(String firstName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address, Cart cart, ArrayList<CreditCard> creditCards, ArrayList<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.cart = cart;
        this.creditCards = creditCards;
        this.orders = orders;
    }
}
