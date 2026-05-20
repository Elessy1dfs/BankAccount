import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import javax.imageio.ImageIO;
import javax.swing.*;
public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private final Runnable backToMenu; 
    private Image playerPlasma, enemyPlasma, victoryImg, defeatedImg, hpPotionImg, manaPotionImg;
    private Image[] backgrounds = new Image[5];
    private boolean facingRight = true;
    private final Set<Integer> pressedKeys = new HashSet<>();
    public BattlePanel(BattleManager manager, Runnable backToMenu) {
        this.manager = manager;
        this.backToMenu = backToMenu;
        this.setFocusable(true);
        try {
            playerPlasma = ImageIO.read(new File("assets/img/plasma_ball.png"));
            enemyPlasma = ImageIO.read(new File("assets/img/plasma_ball.png"));
            victoryImg = ImageIO.read(new File("assets/img/victory.png"));
            defeatedImg = ImageIO.read(new File("assets/img/defeated.png"));
            hpPotionImg = ImageIO.read(new File("assets/img/hp_potion.png"));
            manaPotionImg = ImageIO.read(new File("assets/img/mana_potion.png"));
            backgrounds[1] = ImageIO.read(new File("assets/img/prelim_bg.png"));
            backgrounds[2] = ImageIO.read(new File("assets/img/midterm_bg.png"));
            backgrounds[3] = ImageIO.read(new File("assets/img/prefinal_bg.png"));
            backgrounds[4] = ImageIO.read(new File("assets/img/final_bg.png"));
        } catch (Exception e) { e.printStackTrace(); }
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
                if (e.getKeyCode() == KeyEvent.VK_SPACE) manager.shootPlasma(facingRight);
            }
            @Override public void keyReleased(KeyEvent e) { pressedKeys.remove(e.getKeyCode()); }
        });
