package pacman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LevelMenu extends JPanel implements ActionListener {
    private PacmanMain gameFrame; // Main game frame reference
    private Image pacmanImage; // Pac-Man image

    public LevelMenu(PacmanMain gameFrame) {
        this.gameFrame = gameFrame;
        loadImages(); // Load images
        initLevelMenu(); // Initialize the level menu components
    }

    private void loadImages() {
        pacmanImage = new ImageIcon("path_to_pacman_image.gif").getImage(); // Load the PacMan image
    }

    private void initLevelMenu() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Stack components vertically
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 5)); // Blue border
        setPreferredSize(new Dimension(400, 400)); // Fixed size

        // Title label
        JLabel titleLabel = new JLabel("Select Stage", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 50))); // Space above title
        add(titleLabel);

        // Panel for Pac-Man image
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.BLACK);
        imagePanel.setPreferredSize(new Dimension(100, 100));
        imagePanel.add(new JLabel(new ImageIcon(pacmanImage)));
        add(imagePanel);

        // Panel for stage buttons
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridBagLayout());
        levelPanel.setBackground(Color.BLACK);
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Space between buttons

        // Add stage buttons
        for (int i = 1; i <= 5; i++) {
            gbc.gridx = (i - 1) % 3; // 3 buttons per row
            gbc.gridy = (i - 1) / 3; // Move to next row
            levelPanel.add(createButton("Stage " + i), gbc);
        }

        // Add level panel to main panel
        add(levelPanel);
        add(Box.createRigidArea(new Dimension(0, 60))); // Space below buttons
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Monospaced", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false); // Remove button background
        button.setBorderPainted(false); // Remove border
        button.setFocusPainted(false); // Remove focus border
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.YELLOW);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand().trim(); // Trim whitespace
        int stage;

        try {
            stage = Integer.parseInt(command.replace("Stage", "").trim()); // Extract stage number
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Stage", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit if parsing fails
        }

        // Load the corresponding stage based on the selected stage number
        switch (stage) {
            case 1 -> gameFrame.setContentPane(new PacManGameLevel1()); 
            case 2 -> gameFrame.setContentPane(new PacManGameLevel2());
            case 3 -> gameFrame.setContentPane(new PacManGameLevel3()); 
            case 4 -> gameFrame.setContentPane(new PacManGameLevel4()); 
            case 5 -> gameFrame.setContentPane(new PacManGameLevel5()); 
            default -> {
                JOptionPane.showMessageDialog(this, "Invalid Stage", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Refresh the frame to show the selected stage
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}
