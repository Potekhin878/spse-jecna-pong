package cz.praha.jecna.pong.core;

public class Palka {
    private final int x; // Fixní horizontální pozice pálky
    private int y;       // Vertikální pozice pálky (mění se při pohybu)
    private final int sirka = 15;
    private final int vyska = 100;
    private final int rychlost = 7; // Počet pixelů, o které se pálka posune při jednom kroku

    private final int vyskaOkna;

    public Palka(int startX, int sirkaOkna, int vyskaOkna) {
        this.x = startX;
        this.vyskaOkna = vyskaOkna;
        // Vycentrování pálky na střed vertikální osy při inicializaci
        this.y = vyskaOkna / 2 - vyska / 2;
    }

    // Posun pálky směrem nahoru s kontrolou horního okraje
    public void posunNahoru() {
        y -= rychlost;
        if (y < 0) {
            y = 0; // Zarážka na horním okraji okna
        }
    }

    // Posun pálky směrem dolů s kontrolou dolního okraje
    public void posunDolu() {
        y += rychlost;
        if (y + vyska > vyskaOkna) {
            y = vyskaOkna - vyska; // Zarážka na dolním okraji okna
        }
    }

    // Gettery pro vykreslování a detekci kolizí s míčkem
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSirka() { return sirka; }
    public int getVyska() { return vyska; }
}