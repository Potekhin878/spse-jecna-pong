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

        // Tlačítko pro režim dva hráči
        JButton tlacitkoPvP = new JButton("Hráč vs Hráč");
        tlacitkoPvP.setFont(new Font("Arial", Font.PLAIN, 20));
        tlacitkoPvP.addActionListener(e -> okno.spustHru(false)); // False = není proti PC
        omezeni.gridy = 1;
        add(tlacitkoPvP, omezeni);

        // Tlačítko pro režim proti počítači
        JButton tlacitkoPvE = new JButton("Hráč vs Počítač");
        tlacitkoPvE.setFont(new Font("Arial", Font.PLAIN, 20));
        tlacitkoPvE.addActionListener(e -> okno.spustHru(true)); // True = je proti PC
        omezeni.gridy = 2;
        add(tlacitkoPvE, omezeni);

        JButton tlacitkoKonec = new JButton("Ukončit");
        tlacitkoKonec.setFont(new Font("Arial", Font.PLAIN, 20));
        tlacitkoKonec.addActionListener(e -> System.exit(0));
        omezeni.gridy = 3;
        add(tlacitkoKonec, omezeni);
    }
}