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
        tlacitkoMenu.addActionListener(e -> okno.zobrazObrazovku(TypObrazovky.MENU));
        add(tlacitkoMenu);
    }
}