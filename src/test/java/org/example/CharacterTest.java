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
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthPersonaggioMorto(){
        Character attacco = new Character();
        Character difesa = new Character();
        attacco.combattimento(difesa, 1100);

        assertThat(difesa).isEqualTo(new Character(-100, 1, false));
    }


}