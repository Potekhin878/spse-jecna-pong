package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;

public class HerniOkno extends JFrame {
    private final CardLayout spravceObrazovek;
    private final JPanel hlavniKontejner;

    private final PanelMenu panelMenu;
    private final PanelHry panelHry;
    private final PanelKonecKola panelKonec;

    public HerniOkno() {
        setTitle("SPŠE Ječná - PONG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        spravceObrazovek = new CardLayout();
        hlavniKontejner = new JPanel(spravceObrazovek);

        this.panelHry = new PanelHry(this);
        this.panelMenu = new PanelMenu(this);
        this.panelKonec = new PanelKonecKola(this);

        hlavniKontejner.add(panelMenu, TypObrazovky.MENU.name());
        hlavniKontejner.add(panelHry, TypObrazovky.HRA.name());
        hlavniKontejner.add(panelKonec, TypObrazovky.KONEC_HRY.name());

        add(hlavniKontejner);
        pack();
        setLocationRelativeTo(null);

        // REŠENÍ CHYBY: Volání ošetřené pomocí try-catch bloku
        try {
            zobrazObrazovku(TypObrazovky.MENU);
        } catch (ChybaAplikaceException e) {
            System.err.println("Kritická chyba při startu menu: " + e.getMessage());
        }
    }

    public void spustHru(boolean protiPocitaci) {
        panelHry.nastavitRezim(protiPocitaci);
        // REŠENÍ CHYBY: Volání ošetřené pomocí try-catch bloku
        try {
            zobrazObrazovku(TypObrazovky.HRA);
        } catch (ChybaAplikaceException e) {
            System.err.println("Chyba při spouštění hry: " + e.getMessage());
            // Defenzivní návrat: pokud hra selže, vrátíme uživatele bezpečně do menu
            spravceObrazovek.show(hlavniKontejner, TypObrazovky.MENU.name());
        }
    }

    // Metoda korektně deklaruje, že může vyhodit výjimku
    public void zobrazObrazovku(TypObrazovky typ) throws ChybaAplikaceException {
        if (typ == null) {
            throw new ChybaAplikaceException("Nelze zobrazit neexistující typ obrazovky (null pointer).");
        }

        spravceObrazovek.show(hlavniKontejner, typ.name());
        if (typ == TypObrazovky.HRA) {
            SwingUtilities.invokeLater(() -> {
                panelHry.requestFocusInWindow();
            });
        }
    }

    public JPanel getObrazovku(TypObrazovky typ) {
        switch (typ) {
            case MENU: return panelMenu;
            case HRA: return panelHry;
            case KONEC_HRY: return panelKonec;
            default: return null;
        }
    }
}