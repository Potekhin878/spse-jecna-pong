package cz.praha.jecna.pong.ui;

import javax.swing.*;
import java.awt.*;

public class PanelMenu extends JPanel {
    public PanelMenu(HerniOkno okno) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);
        GridBagConstraints omezeni = new GridBagConstraints();
        omezeni.insets = new Insets(10, 10, 10, 10);
        omezeni.gridx = 0;

        JLabel titulek = new JLabel("PONG HRA");
        titulek.setFont(new Font("Arial", Font.BOLD, 50));
        titulek.setForeground(Color.WHITE);
        omezeni.gridy = 0;
        add(titulek, omezeni);

        JButton tlacitkoHrat = new JButton("Hrát (Hráč vs Hráč)");
        tlacitkoHrat.setFont(new Font("Arial", Font.PLAIN, 20));
        tlacitkoHrat.addActionListener(e -> okno.zobrazObrazovku(TypObrazovky.HRA));
        omezeni.gridy = 1;
        add(tlacitkoHrat, omezeni);

        JButton tlacitkoKonec = new JButton("Ukončit hru");
        tlacitkoKonec.setFont(new Font("Arial", Font.PLAIN, 20));
        tlacitkoKonec.addActionListener(e -> System.exit(0));
        omezeni.gridy = 2;
        add(tlacitkoKonec, omezeni);
    }
}