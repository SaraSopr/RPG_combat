package org.example;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.*;

import static java.lang.Math.min;


@NoArgsConstructor
@AllArgsConstructor
public class Character {
    int health = 1000;
    int level = 1;
    boolean alive = true;
    Type type = Type.MELEE;

    public Character(int health, int level, boolean alive) {
        this.health = health;
        this.level = level;
        this.alive = alive;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Character that = (Character) obj;
        return this.health == that.health &&
                this.level == that.level &&
                this.alive == that.alive;

    }
/*
    public void combattimento(Character difesa, int danno){
        if (this.hashCode()==difesa.hashCode())
            throw new RuntimeException("Errore non si può auto-attaccarsi");
        if (this.level-difesa.level>=5){
            danno = danno / 2;
        } else if (difesa.level - this.level >= 5){
            danno = danno + (danno/2);
        }
        difesa.health = difesa.health - danno;
        if (difesa.health <= 0) {
            difesa.health = 0;
            difesa.alive = false;
        }
    }

    public void cura(Character curato, int cura) {
        if (this.hashCode() != curato.hashCode())
            throw new IllegalArgumentException("Errore non è possibile curare qualcun altro, solo sè stessi");
        if (curato.alive) {
            int nuovaHealth = min(curato.health + cura, 1000);
            curato.health = nuovaHealth;
        }
        else throw new RuntimeException("Errore il personaggio è morto");

    }

*/

    public enum Type {
        MELEE(2),
        RANGED(20);
        final int range;

        public int getRange() {
            return range;
        }

        Type(int range) {
            this.range = range;
        }
    }
}
