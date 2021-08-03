package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * The type Customer.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    /**
     * The constant ROLE_ADMIN.
     */
    public static final int ROLE_ADMIN = 0;

    private static final long serialVersionUID = -1854209356482886781L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private UUID customerUUID = UUID.randomUUID();

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "address_id", nullable = false, unique = true)
    @OneToOne
    private Address address;

    @JoinColumn(name = "cart_id", nullable = true, insertable = false, updatable = false)
    @OneToOne
    private Cart cart;

    @JoinColumn(name = "credit_card_ids", nullable = true, insertable = false, updatable = false)
    @OneToMany
    private List<UUID> creditCards;

    @JoinColumn(name = "order_ids", nullable = true, insertable = false, updatable = false)
    @OneToMany
    private List<UUID> orderDetails;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "date_of_birth", nullable = false, length = 10)
    private String dateOfBirth;

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
     * @param orderDetails the orderDetails
     */
    public Customer(String firstName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address, Cart cart, List<UUID> creditCards, List<UUID> orderDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.cart = cart;
        this.creditCards = creditCards;
        this.orderDetails = orderDetails;
    }
}
