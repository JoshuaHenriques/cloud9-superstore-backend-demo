package org.jayhenri.store_manager.model.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Review.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "review")
public class Review implements Serializable {

    private static final long serialVersionUID = -1286717403046249323L;

    @Id
    @Column(name = "review_id", unique = true, nullable = false)
    private UUID reviewUUID = UUID.randomUUID();

    @Column(name = "text")
    private String text;

    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false, updatable = false)
    private Item item;

    /**
     * Instantiates a new Review.
     *
     * @param text   the text
     * @param rating the rating
     */
    public Review(String text, int rating) {
        this.text = text;
        this.rating = rating;
    }
}
