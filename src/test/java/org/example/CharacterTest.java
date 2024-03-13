package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {
    @Test
    void compara_duepersonaggiuguali_uguali(){
        Character actual = new Character();

        Character expected = new Character();

        assertThat(actual.equals(expected)).isEqualTo(true);

    }


}