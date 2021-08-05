package org.jayhenri.ecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type Cart.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -198235381052492730L;

    @Id
    @Column(name = "cart_id", nullable = false)
    private UUID cartUUID = UUID.randomUUID();

    @JoinColumn(name = "customer_id", unique = true, nullable = true)
    private UUID customerUUID;

    @Type(type = "string-array")
    @Column(
            name = "item_names",
            nullable = true,
            columnDefinition = "text[]"
    )
    private String[] itemsNames;

    @Column(name = "total", nullable = true)
    private Double total;

    /**
     * Instantiates a new Cart.
     *
     * @param total         the total
     */
    public Cart(Double total) {
        this.total = total;
    }
}