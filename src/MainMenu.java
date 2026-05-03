import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class MainMenu extends JPanel {
    private BufferedImage background;
    private JButton startButton;
    private JButton exitButton;

    public MainMenu(Runnable onStart) {
        try {
            // This will look for "menu_bg.png" in your project root
            background = ImageIO.read(new File("menu_bg.png"));
        } catch (Exception e) {
            System.out.println("Menu background not found");
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        // Font to match the pixel aesthetic
        Font pixelFont = new Font("Arial Black", Font.BOLD, 22);

        // Start Button
        startButton = new JButton("START GAME");
        startButton.setFont(pixelFont);
        startButton.setPreferredSize(new Dimension(220, 60));
        startButton.setBackground(new Color(0, 255, 0, 180));
        startButton.setForeground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> onStart.run());

        // Exit Button
        exitButton = new JButton("EXIT GAME");
        exitButton.setFont(pixelFont);
        exitButton.setPreferredSize(new Dimension(220, 60));
        exitButton.setBackground(new Color(255, 0, 0, 180));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(e -> System.exit(0));

        gbc.gridy = 0;
        this.add(startButton, gbc);
        gbc.gridy = 1;
        this.add(exitButton, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        } else {
            g.setColor(new Color(20, 20, 20));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}