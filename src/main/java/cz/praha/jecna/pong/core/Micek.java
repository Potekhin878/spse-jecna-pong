package cz.praha.jecna.pong.core;

/**
 * Třída reprezentující herní míček, která dědí ze společného základu HerniObjekt.
 */
public class Micek extends HerniObjekt {
    private int rychlostX = 4;
    private int rychlostY = 4;
    private static final int VELIKOST_MICKU = 20;

    public Micek(int maximalniSirkaOkna, int maximalniVyskaOkna) {
        super(maximalniSirkaOkna / 2 - VELIKOST_MICKU / 2,
                maximalniVyskaOkna / 2 - VELIKOST_MICKU / 2,
                VELIKOST_MICKU, VELIKOST_MICKU,
                maximalniSirkaOkna, maximalniVyskaOkna);
    }

    public void aktualizuj() {
        x += rychlostX;
        y += rychlostY;

        if (y <= 0 || y >= maximalniVyskaOkna - vyska) {
            rychlostY = -rychlostY;
        }
    }

    public void odrazOdPalky() {
        rychlostX = -rychlostX;
    }

    public void reset() {
        x = maximalniSirkaOkna / 2 - sirka / 2;
        y = maximalniVyskaOkna / 2 - vyska / 2;
        rychlostX = -rychlostX;
    }

    public boolean jeOutVlevo() { return x <= 0; }
    public boolean jeOutVpravo() { return x >= maximalniSirkaOkna - sirka; }
    public int getVelikost() { return sirka; }
}