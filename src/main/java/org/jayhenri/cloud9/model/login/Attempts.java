package org.jayhenri.cloud9.model.login;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Attempts.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "login_attempts")
public class Attempts implements Serializable {

    @Id
    @Column(name = "login_id")
    private UUID loginUUID;

    @Column(name = "attempts")
    private int attempts;
}
