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
        new Timer(30, e -> {
        if (!manager.isGameOver) handleJoystickMovement();
        manager.updateAI();
        manager.player.frameIndex = (manager.player.frameIndex + 1) % 4;
        manager.bot.frameIndex = (manager.bot.frameIndex + 1) % 4;
        // State resets for 'Hit' animation
        if (manager.player.state == 2 && Math.random() > 0.9) manager.player.state = 0;
        if (manager.bot.state == 2 && Math.random() > 0.9) manager.bot.state = 0;
        repaint();
    }).start();
    }
    private void handleJoystickMovement() {
        int dx = 0, dy = 0, speed = 15;
        if (pressedKeys.contains(KeyEvent.VK_LEFT)) { dx -= speed; facingRight = false; }
        if (pressedKeys.contains(KeyEvent.VK_RIGHT)) { dx += speed; facingRight = true; }
        if (pressedKeys.contains(KeyEvent.VK_UP)) dy -= speed;
        if (pressedKeys.contains(KeyEvent.VK_DOWN)) dy += speed;
        manager.player.move(dx, dy);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (backgrounds[manager.currentStage] != null) 
            g2.drawImage(backgrounds[manager.currentStage], 0, 0, getWidth(), getHeight(), null);
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial Black", Font.BOLD, 35));
        
        String title = getStageTitle(manager.currentStage);
            g2.drawString(title, (getWidth() - g2.getFontMetrics().stringWidth(title))/2, 65);
    }
    
    private String getStageTitle(int s) {
        switch(s) { 
            case 1: return "PRELIM: CONTRERAS"; 
            case 2: return "MIDTERM: BOLABOLA"; 
            case 3: return "PRE-FINAL: ABADINAS"; 
            case 4: return "FINALS: TABOADA"; 
            default: return ""; 
        }
    }
}
