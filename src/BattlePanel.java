import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
 
public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private Image plasmaImg;
 
    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.plasmaImg = new ImageIcon("plasma_ball.png").getImage();
 
        // FIX: Use KeyBindings instead of KeyListener for reliable Spacebar
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();
 
        im.put(KeyStroke.getKeyStroke("SPACE"), "attack");
        am.put("attack", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.shootPlasma();
            }
        });
 
        // Movement KeyBindings (Arrow Keys)
        setupMovement(im, am);
 
        // Game Loop: Handles AI and screen refreshes
        Timer gameLoop = new Timer(30, e -> {
            manager.updateAI();
            repaint();
        });
        gameLoop.start();
    }
 
    private void setupMovement(InputMap im, ActionMap am) {
        im.put(KeyStroke.getKeyStroke("LEFT"), "left");
        am.put("left", new AbstractAction() { @Override public void actionPerformed(ActionEvent e) { manager.player.move(-15, 0); } });
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        am.put("right", new AbstractAction() { @Override public void actionPerformed(ActionEvent e) { manager.player.move(15, 0); } });
        im.put(KeyStroke.getKeyStroke("UP"), "up");
        am.put("up", new AbstractAction() { @Override public void actionPerformed(ActionEvent e) { manager.player.move(0, -15); } });
        im.put(KeyStroke.getKeyStroke("DOWN"), "down");
        am.put("down", new AbstractAction() { @Override public void actionPerformed(ActionEvent e) { manager.player.move(0, 15); } });
    }
 
   @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
 
    // 1. Draw Background First
    if (arenaBackground != null) {
        g2.drawImage(arenaBackground, 0, 0, getWidth(), getHeight(), null);
    } else {
        g2.setColor(Color.DARK_GRAY); // Fallback if image is missing
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
 
    // 2. Draw Plasma Ball
    if (manager.player.projectileActive) {
        g2.drawImage(plasmaImg, manager.player.projX, manager.player.projY, 40, 40, null);
    }
 
    // 3. Draw Pets
    g2.drawImage(manager.player.getCurrentFrame(), manager.player.x, manager.player.y, 128, 128, null);
    g2.drawImage(manager.bot.getCurrentFrame(), manager.bot.x, manager.bot.y, 128, 128, null);
 
    // 4. Draw UI (Health Bars) - Call these methods!
    drawUI(g2, manager.player);
    drawUI(g2, manager.bot);
}
 
// Add this helper method if it's missing or corrected
private void drawUI(Graphics2D g, Pet p) {
    g.setColor(Color.WHITE);
    g.drawString(p.name + " (Aura: " + p.hp + ")", p.x, p.y - 20);
    // Background of health bar (Red)
    g.setColor(Color.RED);
    g.fillRect(p.x, p.y - 15, 100, 8);
    // Foreground of health bar (Green)
    g.setColor(Color.GREEN);
    int hpWidth = (int)((float)p.hp / p.maxHp * 100);
    g.fillRect(p.x, p.y - 15, Math.max(0, hpWidth), 8);
} 
}
