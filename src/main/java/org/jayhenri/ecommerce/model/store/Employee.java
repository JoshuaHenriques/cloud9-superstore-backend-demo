package org.jayhenri.ecommerce.model.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jayhenri.ecommerce.model.Login;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "employee_id", unique = true, nullable = false)
    private UUID reviewUUID = UUID.randomUUID();

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

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "image")
    private byte[] image;
}
