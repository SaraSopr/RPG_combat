package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
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
    void calcolaHealth_curaDiUnPersonaggio_risultatoHealthMaggioreMaMinoredi1000() throws Exception {
        Character curante = new Character();
        Character curato = new Character(100, 1, true);
        curante.cura(curato, 100);
        assertThat(curato).isEqualTo(new Character(200, 1, true));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggioMorto_errore() throws Exception {
        Character curante = new Character();
        Character curato = new Character(0, 1, false);
        curante.cura(curato, 100);
        assertThat(curato).isEqualTo("Errore il personaggio è morto");
    }

}