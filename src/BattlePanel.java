import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private final Image plasmaImg;
    private Image[] stageBackgrounds = new Image[5];
    private Image victoryImg, defeatedImg;
    private boolean facingRight = true;

    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.plasmaImg = new ImageIcon("plasma_ball.png").getImage();
        this.victoryImg = new ImageIcon("victory.png").getImage();
        this.defeatedImg = new ImageIcon("defeated.png").getImage();

        // Load stage backgrounds
        stageBackgrounds[1] = new ImageIcon("prelim_bg.png").getImage();
        stageBackgrounds[2] = new ImageIcon("midterm_bg.png").getImage();
        stageBackgrounds[3] = new ImageIcon("prefinal_bg.png").getImage();
        stageBackgrounds[4] = new ImageIcon("final_bg.png").getImage();

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
            @Override public void actionPerformed(ActionEvent e) { if(!manager.isGameOver){ manager.player.move(-15, 0); facingRight = false; repaint(); } }
        });
        am.put("moveRight", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { if(!manager.isGameOver){ manager.player.move(15, 0); facingRight = true; repaint(); } }
        });
        am.put("moveUp", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { if(!manager.isGameOver) { manager.player.move(0, -15); repaint(); } }
        });
        am.put("moveDown", new AbstractAction() {
            @Override public void actionPerformed(ActionEvent e) { if(!manager.isGameOver) { manager.player.move(0, 15); repaint(); } }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Draw Background based on stage
        Image bg = stageBackgrounds[manager.currentStage];
        if (bg != null) g2.drawImage(bg, 0, 0, getWidth(), getHeight(), null);

        // Stage Indicator
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Arial Black", Font.BOLD, 22));
        g2.drawString("STAGE: " + getStageTitle(manager.currentStage), 20, 40);

        if (manager.player.projectileActive) g2.drawImage(plasmaImg, manager.player.projX, manager.player.projY, 40, 40, null);

        // Player
        if (manager.player.hp > 0) {
            BufferedImage pFrame = manager.player.getCurrentFrame();
            if (facingRight) g2.drawImage(pFrame, manager.player.x, manager.player.y, 128, 128, null);
            else g2.drawImage(pFrame, manager.player.x + 128, manager.player.y, -128, 128, null);
        }

        // Bot
        if (manager.bot.hp > 0) {
            g2.drawImage(manager.bot.getCurrentFrame(), manager.bot.x, manager.bot.y, 128, 128, null);
        }

        drawUI(g2, manager.player);
        drawUI(g2, manager.bot);

        if (manager.isGameOver) {
            Image result = manager.playerWon ? victoryImg : defeatedImg;
            g2.drawImage(result, (getWidth()-500)/2, (getHeight()-250)/2, 500, 250, null);
        }
    }

    private String getStageTitle(int stage) {
        switch(stage) {
            case 1: return "PRELIM (Contreras)";
            case 2: return "MIDTERM (Bolabola)";
            case 3: return "PRE-FINAL (Abadinas)";
            case 4: return "FINALS (Taboada)";
            default: return "";
        }
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
