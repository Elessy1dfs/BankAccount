public BattlePanel(BattleManager manager) {
    this.manager = manager;
    this.setFocusable(true); 
    this.requestFocusInWindow();

    try {
        this.arenaBackground = ImageIO.read(new File("arena.png"));
    } catch (Exception e) {
        System.out.println("Arena background missing.");
    }

    this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            requestFocusInWindow();
        }
    });
}
// KEY LISTENER: Controls movement and Spacebar attack
this.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
        int speed = 15;
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) manager.player.x -= speed;
        if (key == KeyEvent.VK_RIGHT) manager.player.x += speed;
        if (key == KeyEvent.VK_UP) manager.player.y -= speed;
        if (key == KeyEvent.VK_DOWN) manager.player.y += speed;

        if (key == KeyEvent.VK_SPACE) {
            checkMeleeRange();
        }
        repaint();
    }
});

private void checkMeleeRange() {
    double distance = Math.sqrt(
            Math.pow(manager.player.x - manager.bot.x, 2) +
            Math.pow(manager.player.y - manager.bot.y, 2)
    );

    if (distance < 100) {
        manager.playerAttack();
    }
}
