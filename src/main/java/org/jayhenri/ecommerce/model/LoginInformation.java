package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LoginInformation {

    @Id
    private UUID uuid;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String dateOfBirth;

    @NotNull
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    public LoginInformation(UUID uuid, String email, String password, String dateOfBirth, String phoneNumber) {
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }
}
