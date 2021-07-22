package org.jayhenri.ecommerce.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false)
    private UUID customerUUID = UUID.randomUUID();

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

    @Column(nullable = false)
    @Embedded
    private Address address;

    @JoinColumn(name = "cartUUID", nullable = true, insertable=false, updatable=false)
    @OneToOne
    private Cart cart;

    @JoinColumn(name = "creditCardUUID", nullable = true, insertable=false, updatable=false)
    @OneToMany
    private List<CreditCard> creditCards;

    @JoinColumn(name = "orderUUID", nullable = true, insertable=false, updatable=false)
    @OneToMany
    private List<OrderDetails> orderDetailsList;

    /**
     * Instantiates a new Customer.
     *
     * @param firstName        the first name
     * @param lastName         the last name
     * @param phoneNumber      the phone number
     * @param email            the email
     * @param password         the password
     * @param dateOfBirth      the date of birth
     * @param address          the address
     * @param cart             the cart
     * @param creditCards      the credit cards
     * @param orderDetailsList the orderDetails
     */
    public Customer(String firstName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address, Cart cart, List<CreditCard> creditCards, List<OrderDetails> orderDetailsList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.cart = cart;
        this.creditCards = creditCards;
        this.orderDetailsList = orderDetailsList;
    }
}
