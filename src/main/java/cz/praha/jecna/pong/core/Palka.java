package cz.praha.jecna.pong.core;

/**
 * Třída reprezentující hráčskou nebo počítačovou pálku, která dědí z HerniObjekt.
 */
public class Palka extends HerniObjekt {
    private static final int RYCHLOST_POHYBU = 7;
    private static final int SIRKA_PALKY = 15;
    private static final int VYSKA_PALKY = 100;

    public Palka(int startovniX, int maximalniSirkaOkna, int maximalniVyskaOkna) {
        super(startovniX, maximalniVyskaOkna / 2 - VYSKA_PALKY / 2,
                SIRKA_PALKY, VYSKA_PALKY,
                maximalniSirkaOkna, maximalniVyskaOkna);
    }

    public void posunNahoru() {
        y -= RYCHLOST_POHYBU;
        if (y < 0) {
            y = 0;
        }
    }

    public void posunDolu() {
        y += RYCHLOST_POHYBU;
        if (y > maximalniVyskaOkna - vyska) {
            y = maximalniVyskaOkna - vyska;
        }
    }
}