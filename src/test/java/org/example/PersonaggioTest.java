package org.example;

import org.junit.jupiter.api.Test;

import static mother.PersonaggioMother.personaggio1LivelloVivo;
import static mother.PersonaggioMother.personaggioPrimoLivelloMorto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PersonaggioTest {
    @Test
    void compara_duePersonaggiUguali_uguali() {
        Personaggio actual = personaggio1LivelloVivo(1000);
        Personaggio expected = personaggio1LivelloVivo(1000);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthPersonaggioVivo() {
        Personaggio actual = personaggio1LivelloVivo(1000);
        actual = actual.ricevoDanno(300);
        assertThat(actual).isEqualTo(new Personaggio(700, 1, true));
    }

    @Test
    void calcoladanno_combattimentoTraDuePersonaggi_risultatoHealthUgualeAZeroPersonaggioMorto() {
        Personaggio actual = personaggio1LivelloVivo(1000);
        actual = actual.ricevoDanno(1100);
        assertThat(actual).isEqualTo(personaggioPrimoLivelloMorto());
    }


    @Test
    void calcolaHealth_curaDiUnPersonaggio_risultatoHealthMaggioreMaMinoredi1000() {
        Personaggio actual = personaggio1LivelloVivo(100);
        actual = actual.ricevoCura(100);
        assertThat(actual).isEqualTo(new Personaggio(200, 1, true));
    }

    @Test
    void calcolaHealth_curaDiUnPersonaggio_risultatoHealthMaggioredi1000() {
        Personaggio actual = personaggio1LivelloVivo(800);
        actual = actual.ricevoCura(300);
        assertThat(actual).isEqualTo(new Personaggio(1000, 1, true));
    }


    @Test
    void calcolaHealth_curaDiUnPersonaggioMorto_errore() {
        Personaggio actual = personaggioPrimoLivelloMorto();

        assertThatThrownBy(() -> {
            actual.ricevoCura(100);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Errore il personaggio è morto");

    }

}