package cz.praha.jecna.pong.core;

public class Micek {
    private int x, y;
    private int velikost = 20; // Průměr míčku v pixelech
    private int rychlostX = 4;  // Rychlost pohybu po horizontální ose
    private int rychlostY = 4;  // Rychlost pohybu po vertikální ose

    private final int sirkaOkna;
    private final int vyskaOkna;

    public Micek(int sirkaOkna, int vyskaOkna) {
        this.sirkaOkna = sirkaOkna;
        this.vyskaOkna = vyskaOkna;
        reset();
    }

    // Resetuje míček do středu obrazovky při startu nebo po vstřelení gólu
    public void reset() {
        this.x = sirkaOkna / 2 - velikost / 2;
        this.y = vyskaOkna / 2 - velikost / 2;
        // Náhodný směr startu (vlevo/vpravo a nahoru/dolů)
        this.rychlostX = Math.random() > 0.5 ? 4 : -4;
        this.rychlostY = Math.random() > 0.5 ? 4 : -4;
    }

    // Aktualizace pozice míčku v každém herním taktu (tik časovače)
    public void aktualizuj() {
        x += rychlostX;
        y += rychlostY;

        // Odraz od horního okraje obrazovky
        if (y <= 0) {
            y = 0;
            rychlostY = -rychlostY; // Otočení směru na ose Y
        }
        // Odraz od dolního okraje obrazovky
        else if (y + velikost >= vyskaOkna) {
            y = vyskaOkna - velikost;
            rychlostY = -rychlostY; // Otočení směru na ose Y
        }
    }

    // Změna směru na ose X při kolizi s pálkou
    public void odrazOdPalky() {
        rychlostX = -rychlostX;
    }

    // Kontrola, zda míček proletěl za levou pálku (bod pro pravého hráče)
    public boolean jeOutVlevo() {
        return x + velikost < 0;
    }

    // Kontrola, zda míček proletěl za pravou pálku (bod pro levého hráče)
    public boolean jeOutVpravo() {
        return x > sirkaOkna;
    }

    // Gettery pro vykreslování komponent v UI
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVelikost() { return velikost; }
}