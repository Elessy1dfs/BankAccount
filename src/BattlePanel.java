import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
 
public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private BufferedImage arenaBackground;
 
    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.setFocusable(true); // Requirement for KeyListener
        try {
            this.arenaBackground = ImageIO.read(new File("arena.png"));
        } catch (Exception e) {
            System.out.println("Arena background missing.");
        }
 
        // KEY LISTENER: Handles Arrow Key movement and Spacebar attack
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int speed = 15;
                int key = e.getKeyCode();
                Pet p = manager.player;
 
                // Movement Logic (Encapsulation: Calling the Pet's move method)
                if (key == KeyEvent.VK_LEFT) p.move(-speed, 0);
                if (key == KeyEvent.VK_RIGHT) p.move(speed, 0);
                if (key == KeyEvent.VK_UP) p.move(0, -speed);
                if (key == KeyEvent.VK_DOWN) p.move(0, speed);
 
                // Attack Logic: Check distance before damaging
                if (key == KeyEvent.VK_SPACE) {
                    if (p.getDistanceTo(manager.bot) < 100) {
                        manager.playerAttack();
                    } else {
                        System.out.println("Too far! Get closer to land the hit.");
                    }
                }
                repaint();
            }
        });
 
        // Animation Timer to cycle through sprite frames
        Timer animTimer = new Timer(150, e -> {
            manager.player.frameIndex = (manager.player.frameIndex + 1) % 4;
            manager.bot.frameIndex = (manager.bot.frameIndex + 1) % 4;
            repaint();
        });
        animTimer.start();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
 
        if (arenaBackground != null) {
            g2.drawImage(arenaBackground, 0, 0, getWidth(), getHeight(), null);
        }
 
        // Draw Pets using their dynamic coordinates
        g2.drawImage(manager.player.getCurrentFrame(), manager.player.x, manager.player.y, 128, 128, null);
        g2.drawImage(manager.bot.getCurrentFrame(), manager.bot.x, manager.bot.y, 128, 128, null);
 
        drawUI(g2, manager.player);
        drawUI(g2, manager.bot);
    }
 
    private void drawUI(Graphics2D g, Pet p) {
        g.setColor(Color.WHITE);
        g.drawString(p.name, p.x, p.y - 20);
        g.setColor(Color.RED);
        g.fillRect(p.x, p.y - 15, 100, 8);
        g.setColor(Color.GREEN);
        int hpWidth = (int)((float)p.hp / p.maxHp * 100);
        g.fillRect(p.x, p.y - 15, hpWidth, 8);
    }
}
