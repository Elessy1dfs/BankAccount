import javax.swing.Timer;

public class BattleManager {
    public Pet player;
    public Pet bot;
    private final Runnable updateUI;
    
    // NEW: State tracking for Victory/Defeat
    public boolean isGameOver = false;
    public boolean playerWon = false;

    public BattleManager(Pet player, Pet bot, Runnable updateUI) {
        this.player = player;
        this.bot = bot;
        this.updateUI = updateUI;
    }

    public void shootPlasma(boolean isFacingRight) {
        if (isGameOver || player.projectileActive) return; 

        player.projectileActive = true;
        player.projDir = isFacingRight ? 1 : -1;
        player.projX = isFacingRight ? (player.x + 80) : (player.x - 20);
        player.projY = player.y + 50;

        Timer projTimer = new Timer(20, null);
        projTimer.addActionListener(e -> {
            player.projX += (15 * player.projDir); 

            if (Math.abs(player.projX - bot.x) < 60 && Math.abs(player.projY - bot.y) < 60) {
                bot.takeDamage(player.aura);
                player.projectileActive = false;
                checkGameOver(); 
                projTimer.stop();
            }

            if (player.projX > 2000 || player.projX < -100) {
                player.projectileActive = false;
                projTimer.stop();
            }
            updateUI.run();
        });
        projTimer.start();
    }

    public void updateAI() {
        if (!isGameOver && bot.hp > 0) {
            bot.chase(player);
            if (bot.getDistanceTo(player) < 60) {
                player.takeDamage(1); 
                checkGameOver();
            }
        }
    }

    private void checkGameOver() {
        if (bot.hp <= 0) {
            isGameOver = true;
            playerWon = true;
        } else if (player.hp <= 0) {
            isGameOver = true;
            playerWon = false;
        }
    }
}
