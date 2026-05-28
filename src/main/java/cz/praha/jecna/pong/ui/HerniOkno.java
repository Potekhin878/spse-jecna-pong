package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;

public class HerniOkno extends JFrame {
    private final CardLayout spravceObrazovek;
    private final JPanel hlavniKontejner;

    // OPRAVA: Deklarace všech panelů jako privátní atributy pro potřeby JUnit testů
    private final PanelMenu panelMenu;
    private final PanelHry panelHry;
    private final PanelKonecKola panelKonec;

    public HerniOkno() {
        setTitle("SPŠE Ječná - PONG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        spravceObrazovek = new CardLayout();
        hlavniKontejner = new JPanel(spravceObrazovek);

        // Inicializace jednotlivých panelů
        this.panelHry = new PanelHry(this);
        this.panelMenu = new PanelMenu(this);
        this.panelKonec = new PanelKonecKola(this);

        hlavniKontejner.add(panelMenu, TypObrazovky.MENU.name());
        hlavniKontejner.add(panelHry, TypObrazovky.HRA.name());
        hlavniKontejner.add(panelKonec, TypObrazovky.KONEC_HRY.name());

        add(hlavniKontejner);
        pack();
        setLocationRelativeTo(null);
        zobrazObrazovku(TypObrazovky.MENU);
    }

    public void spustHru(boolean protiPocitaci) {
        panelHry.nastavitRezim(protiPocitaci);
        zobrazObrazovku(TypObrazovky.HRA);
    }

    public void zobrazObrazovku(TypObrazovky typ) {
        spravceObrazovek.show(hlavniKontejner, typ.name());
        if (typ == TypObrazovky.HRA) {
            SwingUtilities.invokeLater(() -> {
                panelHry.requestFocusInWindow();
            });
        }
    }

    // OPRAVA: Tento getter zde chyběl a JUnit test ho vyžaduje
    public JPanel getObrazovku(TypObrazovky typ) {
        switch (typ) {
            case MENU: return panelMenu;
            case HRA: return panelHry;
            case KONEC_HRY: return panelKonec;
            default: return null;
        }
    }
}