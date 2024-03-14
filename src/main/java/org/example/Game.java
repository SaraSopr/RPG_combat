package org.example;

import java.util.List;

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

    public static void combattimento(Character attacco, Character difesa, int danno, int distanza){
        checkAttacco(attacco, difesa);
        if (!isInRange(distanza, attacco)) return;
        if (isAlleati(attacco.faction, difesa.faction)) return;
        danno = getDanno(danno, attacco.level - difesa.level);
        difesa.health = max(difesa.health - danno, 0);
        if (difesa.health == 0)
            difesa.alive = false;
    }

    private static boolean isAlleati(List<String> faction_attacco, List<String> faction_difesa) {
        for (String s : faction_difesa) {
            if (faction_attacco.contains(s))
                return true;
        }
        return false;
    }

    private static boolean isInRange(int distanza, Character attacco) {
        return attacco.type.getRange() >= distanza;
    }

    private static int getDanno(int danno, int differenzaLivello) {
        int metaDanno = danno / 2;
        if (differenzaLivello >= 5){
            return metaDanno;
        }
        else if (differenzaLivello <= -5){
            return danno + metaDanno;
        }
        return danno;
    }

    private static void checkAttacco(Character attacco, Character difesa) {
        if (attacco.hashCode()== difesa.hashCode())
            throw new RuntimeException("Errore non si può auto-attaccarsi");
    }

    private static void checkCura(Character curatore, Character curato) {
        if ((curatore.hashCode() != curato.hashCode()) && !isAlleati(curatore.faction, curato.faction))
            throw new IllegalArgumentException("Errore non è possibile curare qualcun altro, solo sè stessi o un alleato");
    }


    public static void attaccoAunaCosa(Thing thing, int danno) {
        thing.health = max(thing.health - danno, 0);
        thing.destroyed = thing.health == 0 ? true : thing.destroyed;

    }
}
