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
// Animation Timer
Timer animTimer = new Timer(150, e -> {
    manager.player.frameIndex = (manager.player.frameIndex + 1) % 4;
    manager.bot.frameIndex = (manager.bot.frameIndex + 1) % 4;
    repaint();
});
animTimer.start();

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    if (arenaBackground != null) {
        g2.drawImage(arenaBackground, 0, 0, getWidth(), getHeight(), null);
    }

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
