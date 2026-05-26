package cz.praha.jecna.pong.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HerniOknoTest {
    private HerniOkno okno;

    @BeforeEach
    void inicializace() {
        okno = new HerniOkno();
    }

    @Test
    void testInicializaceObrazovek() {
        assertNotNull(okno.getObrazovku(TypObrazovky.MENU), "Panel menu by měl být inicializován");
        assertNotNull(okno.getObrazovku(TypObrazovky.HRA), "Panel hry by měl být inicializován");
        assertNotNull(okno.getObrazovku(TypObrazovky.KONEC_HRY), "Panel konce hry by měl být inicializován");
    }
}