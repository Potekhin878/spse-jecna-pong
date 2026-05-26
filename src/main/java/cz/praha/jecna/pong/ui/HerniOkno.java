package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Hlavní okno hry, které spravuje přepínání jednotlivých panelů pomocí CardLayout.
 */
public class HerniOkno extends JFrame {
    private final CardLayout spravceRozlozeni;
    private final JPanel hlavniPanel;
    private final Map<TypObrazovky, JPanel> obrazovky = new HashMap<>();

    public HerniOkno() {
        setTitle("PONG Arkáda - SPŠE Ječná");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        spravceRozlozeni = new CardLayout();
        hlavniPanel = new JPanel(spravceRozlozeni);

        obrazovky.put(TypObrazovky.MENU, new PanelMenu(this));
        obrazovky.put(TypObrazovky.HRA, new PanelHry(this));
        obrazovky.put(TypObrazovky.KONEC_HRY, new PanelKonecKola(this));

        for (Map.Entry<TypObrazovky, JPanel> polozka : obrazovky.entrySet()) {
            hlavniPanel.add(polozka.getValue(), polozka.getKey().name());
        }

        add(hlavniPanel);
        zobrazObrazovku(TypObrazovky.MENU);
    }

    public void zobrazObrazovku(TypObrazovky typ) {
        spravceRozlozeni.show(hlavniPanel, typ.name());
    }

    public JPanel getObrazovku(TypObrazovky typ) {
        return obrazovky.get(typ);
    }
}