package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "logins")
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
