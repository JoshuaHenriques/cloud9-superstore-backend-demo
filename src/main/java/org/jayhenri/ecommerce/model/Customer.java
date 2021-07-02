package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// TODO: nullable: false for most field
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    public static final int ROLE_ADMIN = 1;
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

    @Column(nullable = true)
    private Address address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Create_Date", nullable = false)
    private Date createDate;

    private Cart cart;

    private ArrayList<CreditCard> creditCard;

    private ArrayList<Order> orders;

    public Customer(String firstName, String lastName, String phoneNumber, String email, String password, String dateOfBirth, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.cart = new Cart();
        this.creditCard = new ArrayList<CreditCard>();
        this.orders = new ArrayList<Order>();
    }
}
