public class BattleManager {
    public Pet player, bot;
    private final Runnable updateUI;
    public int currentStage = 1;
    public boolean isGameOver = false, playerWon = false;
    private Random rand = new Random();
 
    public BattleManager(Pet player, Runnable updateUI) {
        this.player = player;
        this.updateUI = updateUI;
        spawnNextEnemy();
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
}
