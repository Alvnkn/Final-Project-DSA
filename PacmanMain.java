package pacman;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class PacmanMain extends JFrame {

    public PacmanMain() {
        initUI();
    }

    private void initUI() {
        // Start with the game menu
        add(new GameMenu(this));

        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var ex = new PacmanMain();
            ex.setVisible(true);
        });
    }
}
