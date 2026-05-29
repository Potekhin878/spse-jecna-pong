package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;

public class PanelKonecKola extends JPanel {
    public PanelKonecKola(HerniOkno okno) {
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());

        JLabel text = new JLabel("Konec hry - Výsledky");
        text.setForeground(Color.RED);
        text.setFont(new Font("Arial", Font.BOLD, 30));
        add(text);

        JButton tlacitkoMenu = new JButton("Hlavní menu");
        tlacitkoMenu.addActionListener(e -> {
            // OPRAVA: Tlačítko nyní bezpečně zachytí případnou výjimku při přepnutí obrazovky
            try {
                okno.zobrazObrazovku(TypObrazovky.MENU);
            } catch (ChybaAplikaceException ex) {
                System.err.println("Chyba při návratu do hlavního menu: " + ex.getMessage());
            }
        });
        add(tlacitkoMenu);
    }
}