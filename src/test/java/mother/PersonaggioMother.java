package mother;

import org.example.Personaggio;

public class PersonaggioMother {
    public static Personaggio personaggio1LivelloVivo(int health) {
        return Personaggio.builder()
                .level(1)
                .health(health)
                .alive(true)
                .build();
    }
    public static Personaggio personaggioPrimoLivelloMorto() {
        return Personaggio.builder()
                .level(1)
                .health(0)
                .alive(false)
                .build();
    }
}
