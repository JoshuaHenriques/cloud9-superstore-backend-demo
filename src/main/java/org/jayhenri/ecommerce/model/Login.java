package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = -3706717403082749323L;


    @Id
    @Column(name = "login_id", unique = true, nullable = false)
    private UUID loginUUID = UUID.randomUUID();

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "moderator", nullable = false)
    private boolean moderator = false;

    @Column(name = "admin", nullable = false)
    private boolean admin = false;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;
}
