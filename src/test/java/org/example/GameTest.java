package org.example;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameTest {
    @Test
    void compara_duePersonaggiUguali_uguali(){
        Character actual = new Character();
        Character expected = new Character();
        assertThat(actual.equals(expected)).isEqualTo(true);
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealth(){
        Character attacco = new Character();
        Character difesa = new Character();
        Game.combattimento(attacco, difesa, 300, 2);
        assertThat(difesa.health).isEqualTo(700);
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthPersonaggioVivo(){
        Character attacco = new Character();
        Character difesa = new Character();
        Game.combattimento(attacco, difesa, 300, 2);

        assertThat(difesa).isEqualTo(new Character(700, 1, true));
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthUgualeAZeroPersonaggioMorto(){
        Character attacco = new Character();
        Character difesa = new Character();
        Game.combattimento(attacco, difesa, 1100, 2);
        assertThat(difesa).isEqualTo(new Character(0, 1, false));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggio_risultatoHealthMaggioreMaMinoredi1000() {
        Character curato = new Character(100, 1, true);
        Game.cura(curato, curato, 100);
        assertThat(curato).isEqualTo(new Character(200, 1, true));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggioMorto_errore() {
        Character curato = new Character(0, 1, false);

        assertThatThrownBy(() -> {
            Game.cura(curato, curato, 100);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Errore il personaggio è morto");

    }

    @Test
    void calcolaHealth_attaccoASeStesso_errore(){
        Character attacco = new Character();
        assertThatThrownBy(() -> {
            Game.combattimento(attacco, attacco, 100, 2);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Errore non si può auto-attaccarsi");
    }

    @Test
    void calcolaHealth_curaAqualcunaltro_errore(){
        Character curante = new Character();
        Character curato = new Character();
        assertThatThrownBy(() -> {
            Game.cura(curante, curato, 100);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Errore non è possibile curare qualcun altro, solo sè stessi o un alleato");
    }

    @Test
    void calcolaHealth_attaccoDifferenzaLivelloDiPiuDi5_riduzione50PercAttacco(){
        Character attacco = new Character(700, 10, true);
        Character difesa = new Character(800, 4, true);
        Game.combattimento(attacco, difesa, 400, 2);
        assertThat(difesa).isEqualTo(new Character(600, 4, true));
    }

    @Test
    void calcolaHealth_difesaDifferenzaLivelloDiPiuDi5_riduzione50PercAttacco(){
        Character attacco = new Character(700, 4, true);
        Character difesa = new Character(800, 10, true);
        Game.combattimento(attacco, difesa, 100, 2);
        assertThat(difesa).isEqualTo(new Character(650, 10, true));
    }

    @Test
    void calcolaHealth_distanzaTraIDuePersonaggiMaggioreDiRange_nessunaModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.MELEE);
        Character difesa = new Character(800, 10, true, Character.Type.MELEE);
        Game.combattimento(attacco, difesa, 100, 3);
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(800, 10, true, Character.Type.MELEE));
    }

    @Test
    void calcolaHealth_distanzaTraIDuePersonaggiUgualeARange_ModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.MELEE);
        Character difesa = new Character(800, 10, true, Character.Type.MELEE);
        Game.combattimento(attacco, difesa, 100, 2);
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(700, 10, true, Character.Type.MELEE));
    }

    @Test
    void calcolaHealth_distanzaTraIDuePersonaggiUgualeARangeTypeRanged_ModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.RANGED);
        Character difesa = new Character(800, 10, true, Character.Type.RANGED);
        Game.combattimento(attacco, difesa, 100, Character.Type.RANGED.getRange());
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(700, 10, true, Character.Type.RANGED));
    }

    @Test
    void calcolaHealth_distanzaTraIDuePersonaggiMaggioreDiRangeTypeRanged_ModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.RANGED);
        Character difesa = new Character(800, 10, true, Character.Type.RANGED);
        Game.combattimento(attacco, difesa, 100, Character.Type.RANGED.getRange()+1);
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(800, 10, true, Character.Type.RANGED));
    }

    @Test
    void calcolaHealth_combattimentoTraDueAlleati_NessunaModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.RANGED);
        attacco.addFactions("rossi");
        Character difesa = new Character(800, 10, true, Character.Type.RANGED);
        difesa.addFactions("rossi");
        Game.combattimento(attacco, difesa, 100, Character.Type.RANGED.getRange());
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(800, 10, true, Character.Type.RANGED, asList("rossi")));
    }

    @Test
    void calcolaHealth_combattimentoTraDueAlleati_NonModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.RANGED);
        attacco.addFactions("rossi");
        Character difesa = new Character(800, 10, true, Character.Type.RANGED);
        difesa.addFactions("rossi");
        Game.combattimento(attacco, difesa, 100, Character.Type.RANGED.getRange());
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(800, 10, true, Character.Type.RANGED, asList("rossi")));
    }

    @Test
    void calcolaHealth_combattimentoTraDueNonAlleati_ModificaHealth(){
        Character attacco = new Character(700, 10, true, Character.Type.RANGED);
        attacco.addFactions("rossi");
        Character difesa = new Character(800, 10, true, Character.Type.RANGED);
        difesa.addFactions("verdi");
        Game.combattimento(attacco, difesa, 100, Character.Type.RANGED.getRange());
        assertThat(difesa).usingRecursiveComparison().isEqualTo(new Character(700, 10, true, Character.Type.RANGED, asList("verdi")));
    }

    @Test
    void calcolaHealth_curaTraDueAlleati_ModificaHealth(){
        Character curato = new Character(100, 1, true, Character.Type.RANGED);
        curato.addFactions("rossi");
        Character curante = new Character(100, 1, true, Character.Type.RANGED);
        curante.addFactions("rossi");
        Game.cura(curante, curato, 100);
        assertThat(curato).isEqualTo(new Character(200, 1, true, Character.Type.RANGED, asList("rossi")));
    }

    @Test
    void calcolaHealth_curaTraDueNonAlleati_Errore(){
        Character curato = new Character(100, 1, true, Character.Type.RANGED);
        curato.addFactions("rossi");
        Character curante = new Character(100, 1, true, Character.Type.RANGED);
        curante.addFactions("verdi");
        assertThatThrownBy(() -> {
            Game.cura(curante, curato, 100);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Errore non è possibile curare qualcun altro, solo sè stessi o un alleato");
    }

}