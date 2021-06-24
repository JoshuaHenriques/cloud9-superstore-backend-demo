package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "inventory") // TODO: Change to ArrayList<<Item,s,m,l>> ?
public class OldInventory { // TODO: Refactor

    private UUID uuid;

    public void addProdACount(int prodACount) {
        this.prodACount += prodACount;
    }

    public void addProdBCount(int prodBCount) {
        this.prodBCount += prodBCount;
    }

    public void addProdCCount(int prodCCount) {
        this.prodCCount += prodCCount;
    }

    public void addProdDCount(int prodDCount) {
        this.prodDCount += prodDCount;
    }

    public void addProdECount(int prodECount) {
        this.prodECount += prodECount;
    }

    public void subProdACount(int prodACount) {
        this.prodACount -= prodACount;
    }

    public void subProdBCount(int prodBCount) {
        this.prodBCount -= prodBCount;
    }

    public void subProdCCount(int prodCCount) {
        this.prodCCount -= prodCCount;
    }

    public void subProdDCount(int prodDCount) {
        this.prodDCount -= prodDCount;
    }

    public void subProdECount(int prodECount) {
        this.prodECount -= prodECount;
    }

    private int prodACount = 0;
    private int prodBCount = 0;
    private int prodCCount = 0;
    private int prodDCount = 0;
    private int prodECount = 0;
}
