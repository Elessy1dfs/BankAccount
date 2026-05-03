import javax.swing.Timer;
 
public class BattleManager {
    public Pet player;
    public Pet bot;
    private final Runnable updateUI;
 
    public BattleManager(Pet player, Pet bot, Runnable updateUI) {
        this.player = player;
        this.bot = bot;
        this.updateUI = updateUI;
    }
 
    public void shootPlasma(boolean isFacingRight) {
        if (player.projectileActive) return;
 
        player.projectileActive = true;
        player.projDir = isFacingRight ? 1 : -1;
        player.projX = isFacingRight ? (player.x + 80) : (player.x - 20);
        player.projY = player.y + 50;
 
        Timer projTimer = new Timer(20, null);
        projTimer.addActionListener(e -> {
            player.projX += (15 * player.projDir);
 
            // Hit Detection
            if (Math.abs(player.projX - bot.x) < 60 && Math.abs(player.projY - bot.y) < 60) {
                bot.takeDamage(player.aura);
                player.projectileActive = false;
                projTimer.stop();
            }
 
            // Cleanup
            if (player.projX > 2000 || player.projX < -100) {
                player.projectileActive = false;
                projTimer.stop();
            }
            updateUI.run();
        });
        projTimer.start();
    }
 
    public void updateAI() {
        if (bot.hp > 0) {
            bot.chase(player);
            if (bot.getDistanceTo(player) < 60) {
                player.takeDamage(1); 
            }
        }
    }
}
