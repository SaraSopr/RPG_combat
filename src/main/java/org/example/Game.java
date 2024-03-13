package org.example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static java.lang.Math.max;
import static java.lang.Math.min;



public class Game {
    public static void cura(Character curatore, Character curato, int cura) {
        checkCura(curatore, curato);
        if (curato.alive) {
            int nuovaHealth = min(curato.health + cura, 1000);
            curato.health = nuovaHealth;
        }
        else throw new RuntimeException("Errore il personaggio è morto");

    }

    public static void combattimento(Character attacco,Character difesa, int danno){
        checkAttacco(attacco, difesa);
        danno = getDanno(danno, attacco.level - difesa.level);
        difesa.health = max(difesa.health - danno, 0);
        if (difesa.health == 0)
            difesa.alive = false;
    }

    private static int getDanno(int danno, int differenzaLivello) {
        int mezzoDanno = danno / 2;
        if (differenzaLivello >= 5){
            return mezzoDanno;
        }
        else if (differenzaLivello <= -5){
            return danno + mezzoDanno;
        }
        return danno;
    }

    private static void checkAttacco(Character attacco, Character difesa) {
        if (attacco.hashCode()== difesa.hashCode())
            throw new RuntimeException("Errore non si può auto-attaccarsi");
    }

    private static void checkCura(Character curatore, Character curato) {
        if (curatore.hashCode() != curato.hashCode())
            throw new IllegalArgumentException("Errore non è possibile curare qualcun altro, solo sè stessi");
    }


}