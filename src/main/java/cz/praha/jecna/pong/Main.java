package cz.praha.jecna.pong;

import cz.praha.jecna.pong.ui.HerniOkno;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HerniOkno okno = new HerniOkno();
            okno.setVisible(true);
        });
    }
}