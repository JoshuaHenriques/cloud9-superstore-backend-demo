package org.jayhenri.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Inventory {

    @Id
    private UUID uuid;

    private int prodACount = 0;
    private int prodBCount = 0;
    private int prodCCount = 0;
    private int prodDCount = 0;
    private int prodECount = 0;

    public int count() {

        return prodACount +
                prodBCount +
                prodCCount +
                prodDCount +
                prodECount;
    }

    public int countA() { return this.prodACount; }
    public int countB() { return this.prodBCount; }
    public int countC() { return this.prodCCount; }
    public int countD() { return this.prodDCount; }
    public int countE() { return this.prodECount; }

    public void addToInventory(char x, int y) {

        switch (x) {
            case 'a': prodACount += y;
            case 'b': prodBCount += y;
            case 'c': prodCCount += y;
            case 'd': prodDCount += y;
            case 'e': prodECount += y;
        }
    }

    public void removeFromInventory(char x, int y) {

        switch (x) {
            case 'a': prodACount -= y;
            case 'b': prodBCount -= y;
            case 'c': prodCCount -= y;
            case 'd': prodDCount -= y;
            case 'e': prodECount -= y;
        }
    }
}
