public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private Image playerPlasma, enemyPlasma, victoryImg, defeatedImg, hpPotionImg, manaPotionImg;
    private Image[] backgrounds = new Image[5];
    private boolean facingRight = true;
    private final Set<Integer> pressedKeys = new HashSet<>();
 
    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.setFocusable(true);
        try {
            playerPlasma = ImageIO.read(new File("plasma_ball.png"));
            enemyPlasma = ImageIO.read(new File("plasma_ball.png"));
            victoryImg = ImageIO.read(new File("victory.png"));
            defeatedImg = ImageIO.read(new File("defeated.png"));
            hpPotionImg = ImageIO.read(new File("hp_potion.png"));
            manaPotionImg = ImageIO.read(new File("mana_potion.png"));
            backgrounds[1] = ImageIO.read(new File("prelim_bg.png"));
            backgrounds[2] = ImageIO.read(new File("midterm_bg.png"));
            backgrounds[3] = ImageIO.read(new File("prefinal_bg.png"));
            backgrounds[4] = ImageIO.read(new File("final_bg.png"));
        } catch (Exception e) { e.printStackTrace(); }
        
        addKeyListener(new KeyAdapter() {
        @Override public void keyPressed(KeyEvent e) {
            pressedKeys.add(e.getKeyCode());
            if (e.getKeyCode() == KeyEvent.VK_SPACE) manager.shootPlasma(facingRight);
        }
        @Override public void keyReleased(KeyEvent e) { pressedKeys.remove(e.getKeyCode()); }
    });
    }
    private void handleJoystickMovement() {
        int dx = 0, dy = 0, speed = 15;
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) { dx -= speed; facingRight = false; }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) { dx += speed; facingRight = true; }
        if (pressedKeys.contains(KeyEvent.VK_UP)) dy -= speed;
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) dy += speed;
        manager.player.move(dx, dy);
    }
}
