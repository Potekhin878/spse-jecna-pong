package cz.praha.jecna.pong.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MicekTest {

    @Test
    void testOdrazOdSteny() {
        // Vytvoření virtuálního okna o rozměrech 800x600 px
        Micek micek = new Micek(800, 600);

        // Simulace herní smyčky (200 kroků pohybu míčku)
        for (int i = 0; i < 200; i++) {
            micek.aktualizuj();
        }

        // Ověření fyzikálního enginu: míček nesmí opustit vertikální hranice okna (0-600 px)
        assertTrue(micek.getY() >= 0, "Míček vyletěl nad horní okraj obrazovky!");
        assertTrue(micek.getY() <= 600, "Míček vyletěl pod dolní okraj obrazovky!");
    }
}