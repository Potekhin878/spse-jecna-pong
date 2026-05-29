package cz.praha.jecna.pong.core;

/**
 * Abstraktní třída reprezentující společný základ pro všechny objekty ve hře (Míček, Pálka).
 * Splňuje požadavky na dědičnost a zapouzdření v rámci OOP.
 */
public abstract class HerniObjekt {
    protected int x;
    protected int y;
    protected final int sirka;
    protected final int vyska;
    protected final int maximalniSirkaOkna;
    protected final int maximalniVyskaOkna;

    public HerniObjekt(int x, int y, int sirka, int vyska, int maximalniSirkaOkna, int maximalniVyskaOkna) {
        this.x = x;
        this.y = y;
        this.sirka = sirka;
        this.vyska = vyska;
        this.maximalniSirkaOkna = maximalniSirkaOkna;
        this.maximalniVyskaOkna = maximalniVyskaOkna;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getSirka() { return sirka; }
    public int getVyska() { return vyska; }
}