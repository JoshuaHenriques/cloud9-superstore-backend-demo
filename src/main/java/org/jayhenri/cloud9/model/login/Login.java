package org.jayhenri.cloud9.model.login;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Login.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@TypeDefs(@TypeDef(name = "string-array", typeClass = StringArrayType.class))
@Entity
@Table(name = "login")
public class Login implements Serializable {

    private static final long serialVersionUID = -3706717403082749323L;


    @Id
    @Column(name = "login_id", unique = true, nullable = false)
    private UUID loginUUID = UUID.randomUUID();

    @Column(name = "roles", nullable = false)
    @Type(type = "string-array")
    private String[] roles;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    /**
     * Instantiates a new Login.
     *
     * @param email            the email
     * @param phoneNumber      the phone number
     * @param password         the password
     * @param roles            the roles
     * @param accountNonLocked the account non locked
     */
    public Login(String email, String phoneNumber, String password, String[] roles, boolean accountNonLocked) {
        this.roles = roles;
        this.enabled = accountNonLocked;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
