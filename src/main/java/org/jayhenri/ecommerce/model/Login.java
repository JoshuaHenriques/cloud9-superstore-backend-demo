package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.UUID;

@Getter
@Setter
@Embeddable
public class Login {

    @Id
    private UUID uuid;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false, length = 6)
    private String dateOfBirth;

    public Login(UUID uuid, String email, String password, String dateOfBirth, String phoneNumber) {
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
