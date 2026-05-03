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
            background = ImageIO.read(new File("menu_bg.png"));
        } catch (Exception e) {
            System.out.println("Menu background not found");
        }

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}