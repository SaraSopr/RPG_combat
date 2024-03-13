package org.example;

import lombok.Builder;
import lombok.Data;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Data
@Builder
public class Personaggio {
    final int health;
    final int level;
    final boolean alive;

    public Personaggio ricevoDanno(int danno) {
        int nuovaHealth = max(this.getHealth() - danno, 0);
        return new Personaggio(nuovaHealth, this.getLevel(), nuovaHealth != 0);
    }

    public Personaggio ricevoCura(int cura) {
        checkMorte();
        int nuovaHealth = min(this.getHealth() + cura, 1000);
        return new Personaggio(nuovaHealth, this.getLevel(), true);
    }

    private void checkMorte() {
        if (!this.isAlive())
            throw new RuntimeException("Errore il personaggio è morto");
    }
}
