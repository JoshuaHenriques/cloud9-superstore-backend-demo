package org.jayhenri.cloud9.model.customer;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.login.Login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Customer.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -1854209356482886781L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private UUID customerUUID = UUID.randomUUID();

    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @Column(name = "phone_number", nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "date_of_birth", nullable = false, length = 10)
    private String dateOfBirth;

    @OneToOne
    @JoinColumn(name="login_id", unique=true, nullable=false, updatable=false)
    private Login login;

    // @OneToOne
    // @JoinColumn(name="cart_id", unique=true, nullable=false, updatable=false)
    // private Cart cart;

    @OneToOne
    @JoinColumn(name="address_id", unique=true, nullable=false, updatable=false)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<CreditCard> creditCards;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Orders> orders;

    /**
     * Instantiates a new Customer.
     *
     * @param email       the email
     * @param login       the login
     * @param address     the address
     * @param wallet      the wallet
     * @param orders      the orders
     * @param firstName   the first name
     * @param lastName    the last name
     * @param phoneNumber the phone number
     * @param dateOfBirth the date of birth
     */
    public Customer(String email, Login login, Address address, Set<CreditCard> wallet, Set<Orders> orders,
            String firstName, String lastName, String phoneNumber, String dateOfBirth) {
        
        this.email = email;
        this.login = login;
        this.address = address;
        this.creditCards = wallet;
        this.orders = orders;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth; 
    } 
}
