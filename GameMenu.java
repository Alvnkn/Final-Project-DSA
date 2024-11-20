package pacman;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameMenu extends JPanel implements ActionListener {

    private PacmanMain gameFrame; // Renamed to PacmanMain
    private JButton startButton;
    private JButton controlsButton;
    private JButton creditsButton;
    private Image pacmanImage;

    public GameMenu(PacmanMain gameFrame) { // Updated constructor
        this.gameFrame = gameFrame;
        loadImages();
        initMenu();
    }

    private void loadImages() {
        pacmanImage = new ImageIcon("C:/Data Structures (Project)/right.gif").getImage(); // Load the PacMan image
    }

    private void initMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Stack components vertically
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); // Blue border

        // Title label
        JLabel titleLabel = new JLabel("PACMAN", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 48));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 90))); // Space above title
        add(titleLabel);

        // Panel for Pac-Man image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.BLACK);
        imagePanel.setPreferredSize(new Dimension(100, 100)); // Set smaller size
        imagePanel.add(new JLabel(new ImageIcon(pacmanImage)));
        add(imagePanel);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.BLACK);

        // Start Game button
        startButton = createButton("Start Game");
        buttonPanel.add(startButton);

        // Controls button
        controlsButton = createButton("Controls");
        buttonPanel.add(controlsButton);

        // Credits button
        creditsButton = createButton("Credits");
        buttonPanel.add(creditsButton);

        // Add space below the credits button
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 30))); // Adds space below credits

        add(buttonPanel);
        setPreferredSize(new Dimension(400, 400));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false); // Remove background
        button.setBorderPainted(false); // Remove border
        button.setFocusPainted(false); // Remove focus border
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button

        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.YELLOW); // Change to yellow on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE); // Revert to white when not hovered
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            gameFrame.setContentPane(new LevelMenu(gameFrame)); // Switch to the level menu
        } else if (e.getSource() == controlsButton) {
            JOptionPane.showMessageDialog(this, "Controls:\nUse arrow keys to move Pacman.", "Controls", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == creditsButton) {
            JOptionPane.showMessageDialog(this, "TO BE ADDED - Joshua", "Credits", JOptionPane.INFORMATION_MESSAGE);
        }
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}
