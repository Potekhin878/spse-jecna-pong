package cz.praha.jecna.pong.ui;

import cz.praha.jecna.pong.core.Micek;
import cz.praha.jecna.pong.core.Palka;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PanelHry extends JPanel implements ActionListener {
    private static final int MAXIMALNI_SKORE = 5;

    private final HerniOkno hlavniOkno;
    private final Micek herniMicek;
    private final Palka levaPalka;
    private final Palka pravaPalka;
    private final Timer herniCasovac;

    private boolean jeProtiPocitaci = false;
    private int skoreLeva = 0;
    private int skorePrava = 0;

    // Počítadlo snímků pro zpomalení reakce bota
    private int pocitadloSnimku = 0;

    private boolean klavesaW, klavesaS, klavesaUp, klavesaDown;

    public PanelHry(HerniOkno okno) {
        this.hlavniOkno = okno;

        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);

        herniMicek = new Micek(800, 600);
        levaPalka = new Palka(30, 800, 600);
        pravaPalka = new Palka(755, 800, 600);

        addKeyListener(new OvladaniKlavesnice());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        herniCasovac = new Timer(16, this);
        herniCasovac.start();
    }

    public void nastavitRezim(boolean protiPocitaci) {
        this.jeProtiPocitaci = protiPocitaci;
        this.skoreLeva = 0;
        this.skorePrava = 0;
        this.pocitadloSnimku = 0;
        herniMicek.reset();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        herniMicek.aktualizuj();
        pocitadloSnimku++; // Zvyšujeme počítadlo s každým tikem časovače

        // Pohyb levé pálky (Hráč 1)
        if (klavesaW) levaPalka.posunNahoru();
        if (klavesaS) levaPalka.posunDolu();

        // Pohyb pravé pálky (Inteligentní zpomalené AI)
        if (jeProtiPocitaci) {
            // Počítač reaguje pouze každý druhý snímek (efektivně snižuje svou rychlost na polovinu)
            if (pocitadloSnimku % 2 == 0) {
                int stredPalky = pravaPalka.getY() + (pravaPalka.getVyska() / 2);
                int stredMicku = herniMicek.getY() + (herniMicek.getVelikost() / 2);

                if (stredMicku < stredPalky - 10) {
                    pravaPalka.posunNahoru();
                } else if (stredMicku > stredPalky + 10) {
                    pravaPalka.posunDolu();
                }
            }
        } else {
            // Režim dvou hráčů (Hráč 2 - šipky)
            if (klavesaUp) pravaPalka.posunNahoru();
            if (klavesaDown) pravaPalka.posunDolu();
        }

        // Zpracování kolizí míčku s pálkami
        if (checkCollision(levaPalka) || checkCollision(pravaPalka)) {
            herniMicek.odrazOdPalky();
        }

        // Kontrola bodování
        if (herniMicek.jeOutVlevo()) {
            skorePrava++;
            zkontrolujKonecHRY();
        } else if (herniMicek.jeOutVpravo()) {
            skoreLeva++;
            zkontrolujKonecHRY();
        }

        repaint();
    }

    private void zkontrolujKonecHRY() {
        if (skoreLeva >= MAXIMALNI_SKORE || skorePrava >= MAXIMALNI_SKORE) {
            this.skoreLeva = 0;
            this.skorePrava = 0;
            herniMicek.reset();

            // OPRAVA: Přidán try-catch blok pro bezpečné ošetření výjimky při přepnutí obrazovky
            try {
                hlavniOkno.zobrazObrazovku(TypObrazovky.KONEC_HRY);
            } catch (ChybaAplikaceException e) {
                System.err.println("Chyba při ukončování hry a přechodu na výsledky: " + e.getMessage());
            }
        } else {
            herniMicek.reset();
        }
    }

    private boolean checkCollision(Palka p) {
        return herniMicek.getX() < p.getX() + p.getSirka() &&
                herniMicek.getX() + herniMicek.getVelikost() > p.getX() &&
                herniMicek.getY() < p.getY() + p.getVyska() &&
                herniMicek.getY() + herniMicek.getVelikost() > p.getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vykreslení středové čáry
        g2d.setColor(Color.DARK_GRAY);
        for (int i = 0; i < 600; i += 30) {
            g2d.fillRect(800 / 2 - 2, i, 4, 15);
        }

        // Vykreslení aktuálního skóre
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Consolas", Font.BOLD, 60));
        g2d.drawString(String.valueOf(skoreLeva), 800 / 2 - 100, 70);
        g2d.drawString(String.valueOf(skorePrava), 800 / 2 + 50, 70);

        // Vykreslení herních komponent
        g2d.fillOval(herniMicek.getX(), herniMicek.getY(), herniMicek.getVelikost(), herniMicek.getVelikost());
        g2d.fillRect(levaPalka.getX(), levaPalka.getY(), levaPalka.getSirka(), levaPalka.getVyska());
        g2d.fillRect(pravaPalka.getX(), pravaPalka.getY(), pravaPalka.getSirka(), pravaPalka.getVyska());

        Toolkit.getDefaultToolkit().sync();
    }

    private class OvladaniKlavesnice extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int k = e.getKeyCode();
            if (k == KeyEvent.VK_W) klavesaW = true;
            if (k == KeyEvent.VK_S) klavesaS = true;
            if (k == KeyEvent.VK_UP) klavesaUp = true;
            if (k == KeyEvent.VK_DOWN) klavesaDown = true;
        }
        public void keyReleased(KeyEvent e) {
            int k = e.getKeyCode();
            if (k == KeyEvent.VK_W) klavesaW = false;
            if (k == KeyEvent.VK_S) klavesaS = false;
            if (k == KeyEvent.VK_UP) klavesaUp = false;
            if (k == KeyEvent.VK_DOWN) klavesaDown = false;
        }
    }
}