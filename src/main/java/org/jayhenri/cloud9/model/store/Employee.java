package org.jayhenri.cloud9.model.store;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.cloud9.model.customer.Address;
import org.jayhenri.cloud9.model.login.Login;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

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

    @OneToOne
    @JoinColumn(name = "address_id", unique = true, nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "login_id", nullable = false, unique = true)
    private Login login;

    @OneToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

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
    public Employee(Login login, Store store, String firstName, String lastName, String phoneNumber, String dateOfBirth) {
        this.login = login;
        this.store = store;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
