package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;

public class PanelHry extends JPanel {
    public PanelHry(HerniOkno okno) {
        setBackground(Color.DARK_GRAY);
        setLayout(new FlowLayout());

        JLabel text = new JLabel("Herní obrazovka (Simulace)");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.PLAIN, 24));
        add(text);

        JButton tlacitkoKonec = new JButton("Simulovat konec zápasu");
        tlacitkoKonec.addActionListener(e -> okno.zobrazObrazovku(TypObrazovky.KONEC_HRY));
        add(tlacitkoKonec);
    }
}