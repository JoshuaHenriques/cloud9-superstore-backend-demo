package org.jayhenri.cloud9.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.cloud9.model.login.Login;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * The type Customer.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Table(name = "customer")
public class Customer implements Serializable {


    private static final long serialVersionUID = -1854209356482886781L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private UUID customerUUID = UUID.randomUUID();

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @OneToOne
    @JoinColumn(name = "login_id", nullable = false)
    private Login login;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "wallet",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "credit_card_id")
    )
    private Set<CreditCard> wallet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_items",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Orders> orders;

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false, length = 10)
    private String dateOfBirth;


    /**
     * Instantiates a new Customer.
     *
     * @param email       the email
     * @param login       the login
     * @param cart        the cart
     * @param address     the address
     * @param wallet      the wallet
     * @param orders      the orders
     * @param firstName   the first name
     * @param lastName    the last name
     * @param phoneNumber the phone number
     * @param dateOfBirth the date of birth
     */
    public Customer(String email, Login login, Cart cart, Address address, Set<CreditCard> wallet, Set<Orders> orders, String firstName, String lastName, String phoneNumber, String dateOfBirth) {
        this.email = email;
        this.login = login;
        this.cart = cart;
        this.address = address;
        this.wallet = wallet;
        this.orders = orders;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
