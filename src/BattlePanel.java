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
    private boolean facingRight = true;
 
    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.plasmaImg = new ImageIcon("plasma_ball.png").getImage();
 
        try {
            this.arenaBackground = ImageIO.read(new File("arena.png"));
        } catch (Exception e) {
            System.out.println("arena.png missing.");
        }
 
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
 
        im.put(KeyStroke.getKeyStroke("SPACE"), "attack");
        am.put("attack", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.shootPlasma(facingRight); }
        });
 
        setupMovement(im, am);
 
        Timer gameLoop = new Timer(30, e -> {
            manager.updateAI();
            manager.player.frameIndex = (manager.player.frameIndex + 1) % 4;
            manager.bot.frameIndex = (manager.bot.frameIndex + 1) % 4;
            repaint();
        });
        gameLoop.start();
    }
 
    private void setupMovement(InputMap im, ActionMap am) {
        im.put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        im.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        im.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
 
        am.put("moveLeft", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(-15, 0); facingRight = false; repaint(); }
        });
        am.put("moveRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { manager.player.move(15, 0); facingRight = true; repaint(); }
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
 
        if (arenaBackground != null) g2.drawImage(arenaBackground, 0, 0, getWidth(), getHeight(), null);
 
        if (manager.player.projectileActive) g2.drawImage(plasmaImg, manager.player.projX, manager.player.projY, 40, 40, null);
 
        // Player with Flip
        BufferedImage pFrame = manager.player.getCurrentFrame();
        if (facingRight) g2.drawImage(pFrame, manager.player.x, manager.player.y, 128, 128, null);
        else g2.drawImage(pFrame, manager.player.x + 128, manager.player.y, -128, 128, null);
 
        // Bot
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
        g.fillRect(p.x, p.y - 15, Math.max(0, hpWidth), 8);
    }
}
