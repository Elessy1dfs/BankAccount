import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private final Image plasmaImg;
    private Image arenaBackground; 

    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        
        // Load the Plasma Ball image
        this.plasmaImg = new ImageIcon("plasma_ball.png").getImage();

        // FIX: Added the loading logic for the arena background
        try {
            this.arenaBackground = ImageIO.read(new File("arena.png"));
        } catch (Exception e) {
            System.out.println("Error: arena.png not found in project folder.");
        }

        // Use KeyBindings for reliable controls even when focus shifts
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        // Attack Binding
        im.put(KeyStroke.getKeyStroke("SPACE"), "attack");
        am.put("attack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.shootPlasma();
            }
        });

        setupMovement(im, am);

        // Game Loop: Updates AI and handles animations/physics
        Timer gameLoop = new Timer(30, e -> {
            manager.updateAI();
            
            // Cycle animation frames for both characters
            manager.player.frameIndex = (manager.player.frameIndex + 1) % 4;
            manager.bot.frameIndex = (manager.bot.frameIndex + 1) % 4;
            
            repaint();
        });
        gameLoop.start();
    }

    private void setupMovement(InputMap im, ActionMap am) {
        // Mapping keys to Action names
        im.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        im.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        im.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");

        // Defining the actions and forcing a repaint so you see movement instantly
        am.put("moveLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(-15, 0); repaint(); }
        });
        am.put("moveRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(15, 0); repaint(); }
        });
        am.put("moveUp", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(0, -15); repaint(); }
        });
        am.put("moveDown", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(0, 15); repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // 1. Draw Background First (Layer 0)
        if (arenaBackground != null) {
            g2.drawImage(arenaBackground, 0, 0, getWidth(), getHeight(), null);
        } else {
            g2.setColor(Color.DARK_GRAY);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        // 2. Draw Projectiles (Layer 1)
        if (manager.player.projectileActive) {
            g2.drawImage(plasmaImg, manager.player.projX, manager.player.projY, 40, 40, null);
        }

        // 3. Draw Characters (Layer 2)
        // Note: Using 128x128 for clear visibility of your Aseprite work
        g2.drawImage(manager.player.getCurrentFrame(), manager.player.x, manager.player.y, 128, 128, null);
        g2.drawImage(manager.bot.getCurrentFrame(), manager.bot.x, manager.bot.y, 128, 128, null);

        // 4. Draw UI Elements (Layer 3 - Top)
        drawUI(g2, manager.player);
        drawUI(g2, manager.bot);
    }

    private void drawUI(Graphics2D g, Pet p) {
        // Name and Aura text
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString(p.name, p.x, p.y - 20);

        // Health bar background (Red for lost HP)
        g.setColor(Color.RED);
        g.fillRect(p.x, p.y - 15, 100, 8);

        // Health bar foreground (Green for current HP)
        g.setColor(Color.GREEN);
        int hpWidth = (int)((float)p.hp / p.maxHp * 100);
        g.fillRect(p.x, p.y - 15, Math.max(0, hpWidth), 8);
    }
}
