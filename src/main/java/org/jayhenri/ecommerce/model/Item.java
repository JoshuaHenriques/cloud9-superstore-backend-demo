package org.jayhenri.ecommerce.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private Long UUID;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private Rating rating; // TODO: Implement later

    @NotNull
    @Column
    private double price;

    @NotNull
    @Column
    private ArrayList<Tag> tags; // TODO: Implement later
}
