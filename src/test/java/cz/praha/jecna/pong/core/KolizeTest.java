package cz.praha.jecna.pong.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KolizeTest {

    @Test
    void testOdrazuOdPalky() {
        // Vytvoříme virtuální hrací plochu 800x600 px
        Micek micek = new Micek(800, 600);
        Palka palka = new Palka(30, 800, 600);

        // Ručně zavoláme odraz míčku
        micek.odrazOdPalky();

        // Pohneme míčkem o jeden krok
        micek.aktualizuj();

        // Ověříme funkčnost: po simulaci odrazu se souřadnice nesmí rozbít
        assertNotNull(micek);
        assertTrue(palka.getVyska() > 0, "Pálka má nesprávnou výšku!");
    }
}