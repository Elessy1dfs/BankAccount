public class BattleManager {
    public Pet player, bot;
    private final Runnable updateUI;
    public int currentStage = 1;
    public boolean isGameOver = false, playerWon = false;
    private Random rand = new Random();
    
    public List<Potion> potions = new ArrayList<>();
 
    public BattleManager(Pet player, Runnable updateUI) {
        this.player = player;
        this.updateUI = updateUI;
        spawnNextEnemy();

          Timer manaRegen = new Timer(1000, e -> {
        if (!isGameOver && player.mana < player.maxMana) 
            player.mana = Math.min(player.maxMana, player.mana + 5);
    });
    manaRegen.start();

        Timer potionSpawner = new Timer(4000, e -> {
        if (!isGameOver) {
            int type = rand.nextInt(2); // 0 for HP, 1 for Mana
            int rx = Math.max(100, Math.min(player.x + (rand.nextInt(500) - 250), 1100));
            int ry = Math.max(150, Math.min(player.y + (rand.nextInt(500) - 250), 650));
            potions.add(new Potion(rx, ry, type));
        }
    });
    potionSpawner.start();
        
    }
    
 
    public void spawnNextEnemy() {
        player.x = 100; player.y = 400;
        // Reset lists for new stage
        enemyProjectiles.clear();
        potions.clear();
        switch (currentStage) {
            case 1: bot = new Pet("Contreras", 100, 12, 5, "contreras.png", 800, 400) {}; break;
            case 2: bot = new Pet("Bolabola", 150, 18, 15, "bolabola.png", 800, 400) {}; break;
            case 3: bot = new Pet("Abadinas", 250, 25, 30, "abadinas.png", 800, 400) {}; break;
            case 4: bot = new Pet("Taboada", 500, 40, 50, "taboada.png", 800, 400) {}; break;
        }
    }

    private void handlePotionPickup() {
        for (int i = potions.size() - 1; i >= 0; i--) {
            Potion p = potions.get(i);
            if (Math.abs((player.x + 64) - (p.x + 40)) < 70 && Math.abs((player.y + 64) - (p.y + 40)) < 70) {
                if (p.type == 0) player.hp = Math.min(player.maxHp, player.hp + 25);
                else player.mana = Math.min(player.maxMana, player.mana + 40);
                potions.remove(i);
            }
        }
    }
}
