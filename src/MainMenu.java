import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMenu extends JPanel {
    private BufferedImage background;
    private JButton startButton;
    private JButton exitButton;

    public MainMenu(Runnable onStart) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}