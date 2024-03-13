package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
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
        attacco.combattimento(difesa, 300);
        assertThat(difesa.health).isEqualTo(700);
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthPersonaggioVivo(){
        Character attacco = new Character();
        Character difesa = new Character();
        attacco.combattimento(difesa, 300);

        assertThat(difesa).isEqualTo(new Character(700, 1, true));
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthUgualeAZeroPersonaggioMorto(){
        Character attacco = new Character();
        Character difesa = new Character();
        attacco.combattimento(difesa, 1100);
        assertThat(difesa).isEqualTo(new Character(0, 1, false));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggio_risultatoHealthMaggioreMaMinoredi1000() {
        Character curato = new Character(100, 1, true);
        curato.cura(curato, 100);
        assertThat(curato).isEqualTo(new Character(200, 1, true));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggioMorto_errore() {
        Character curato = new Character(0, 1, false);

        assertThatThrownBy(() -> {
            curato.cura(curato, 100);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Errore il personaggio è morto");

    }

    @Test
    void calcolaHealth_attaccoASeStesso_errore(){
        Character attacco = new Character();
        assertThatThrownBy(() -> {
            attacco.combattimento(attacco, 100);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Errore non si può auto-attaccarsi");
    }

    @Test
    void calcolaHealth_curaAqualcunaltro_errore(){
        Character curante = new Character();
        Character curato = new Character();
        assertThatThrownBy(() -> {
            curante.cura(curato, 100);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Errore non è possibile curare qualcun altro, solo sè stessi");
    }

    @Test
    void calcolaHealth_attaccoDifferenzaLivelloDiPiuDi5_riduzione50PercAttacco(){
        Character attacco = new Character(700, 10, true);
        Character difesa = new Character(800, 4, true);
        attacco.combattimento(difesa, 400);
        assertThat(difesa).isEqualTo(new Character(600, 4, true));
    }

    @Test
    void calcolaHealth_difesaDifferenzaLivelloDiPiuDi5_riduzione50PercAttacco(){
        Character attacco = new Character(700, 4, true);
        Character difesa = new Character(800, 10, true);
        attacco.combattimento(difesa, 100);
        assertThat(difesa).isEqualTo(new Character(650, 10, true));
    }


}