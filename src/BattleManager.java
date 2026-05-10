import javax.swing.Timer;

public class BattleManager {
    public Pet player;
    public Pet bot;
    private final Runnable updateUI;

    public boolean isGameOver = false;
    public boolean playerWon = false;
    public int currentStage = 1; // 1: Prelim, 2: Midterm, 3: PreFinal, 4: Final

    public BattleManager(Pet player, Runnable updateUI) {
        this.player = player;
        this.updateUI = updateUI;
        spawnNextEnemy(); // Start with the first stage
    }

    public void spawnNextEnemy() {
        // Reset player state for the new round
        player.x = 100;
        player.y = 400;
        player.projectileActive = false;

        // Difficulty Scaling: HP, Attack (Aura), Defense (Rizz)
        switch (currentStage) {
            case 1:
                bot = new Pet("Contreras", 80, 10, 5, "contreras.png", 800, 400) {};
                break;
            case 2:
                bot = new Pet("Bolabola", 130, 18, 15, "bolabola.png", 800, 400) {};
                break;
            case 3:
                bot = new Pet("Abadinas", 200, 25, 25, "abadinas.png", 800, 400) {};
                break;
            case 4:
                bot = new Pet("Taboada", 350, 40, 45, "taboada.png", 800, 400) {};
                break;
        }
    }

    public void shootPlasma(boolean isFacingRight) {
        if (isGameOver || player.projectileActive) return;

        player.projectileActive = true;
        player.projDir = isFacingRight ? 1 : -1;
        player.projX = isFacingRight ? (player.x + 80) : (player.x - 20);
        player.projY = player.y + 50;

        Timer projTimer = new Timer(20, null);
        projTimer.addActionListener(e -> {
            player.projX += (18 * player.projDir);

            if (Math.abs(player.projX - bot.x) < 60 && Math.abs(player.projY - bot.y) < 60) {
                bot.takeDamage(player.aura);
                player.projectileActive = false;
                checkWinCondition();
                projTimer.stop();
            }

            if (player.projX > 2000 || player.projX < -200) {
                player.projectileActive = false;
                projTimer.stop();
            }
            updateUI.run();
        });
        projTimer.start();
    }

    private void checkWinCondition() {
        if (bot.hp <= 0) {
            if (currentStage < 4) {
                currentStage++;
                spawnNextEnemy(); // Move to the next exam
            } else {
                isGameOver = true;
                playerWon = true; // Graduation achieved!
            }
        }
    }

    public void updateAI() {
        if (!isGameOver && bot.hp > 0) {
            bot.chase(player);
            if (bot.getDistanceTo(player) < 60) {
                player.takeDamage(2); // Enemies deal constant damage on contact
                if (player.hp <= 0) {
                    isGameOver = true;
                    playerWon = false;
                }
            }
        }
    }
}