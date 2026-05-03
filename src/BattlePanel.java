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
        // Draw characters
        g.drawImage(manager.player.getCurrentFrame(), manager.player.x, manager.player.y, 100, 100, null);
        g.drawImage(manager.bot.getCurrentFrame(), manager.bot.x, manager.bot.y, 100, 100, null);
 
        // Draw Plasma Ball if active
        if (manager.player.projectileActive) {
            g.drawImage(plasmaImg, manager.player.projX, manager.player.projY, 40, 40, null);
        }
        // Draw Health bars...
    }
}
