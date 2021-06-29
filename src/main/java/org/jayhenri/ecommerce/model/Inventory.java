package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.javatuples.Quartet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "inventory") // TODO: Change to ArrayList<<Item,s,m,l>> ?
public class Inventory { // TODO: Refactor

    @Id
    private UUID uuid;

    @Column
    private ArrayList<Quartet<UUID, Integer, Integer, Integer>> items;

    public Inventory() {
        items = new ArrayList<>();
    }
}
