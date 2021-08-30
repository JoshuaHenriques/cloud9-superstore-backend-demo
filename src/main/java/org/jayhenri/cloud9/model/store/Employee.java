package org.jayhenri.cloud9.model.store;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.login.Login;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Employee.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id", unique = true, nullable = false)
    private UUID employeeUUID = UUID.randomUUID();

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "image")
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    /**
     * Instantiates a new Employee.
     *
     * @param login       the login
     * @param store       the store
     * @param firstName   the first name
     * @param lastName    the last name
     * @param phoneNumber the phone number
     * @param dateOfBirth the date of birth
     */
    public Employee(String email, Address address, Login login, Store store, String firstName, String lastName, String phoneNumber, String dateOfBirth) {
        this.email = email;
        this.address = address;
        this.login = login;
        this.store = store;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
