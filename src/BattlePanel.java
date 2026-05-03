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
