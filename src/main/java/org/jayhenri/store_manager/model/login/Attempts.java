package org.jayhenri.store_manager.model.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private UUID attemptsUUID;

    @MapsId
    @OneToOne
    @JoinColumn(name = "login_id", unique = true, nullable = false, updatable = false)
    private Login login;

    @Column(name = "attempts")
    private int attempts = 0;
}
