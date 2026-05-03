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
 
    public void shootPlasma() {
        if (player.projectileActive) return; // Only one ball at a time
 
        player.projectileActive = true;
        player.projX = player.x + 50;
        player.projY = player.y + 50;
 
        Timer projTimer = new Timer(20, null);
        projTimer.addActionListener(e -> {
            player.projX += 10; // Moves right
 
            // Collision Detection
            if (Math.abs(player.projX - bot.x) < 50 && Math.abs(player.projY - bot.y) < 50) {
                bot.takeDamage(player.aura);
                player.projectileActive = false;
                projTimer.stop();
            }
 
            // Out of bounds
            if (player.projX > 2000) {
                player.projectileActive = false;
                projTimer.stop();
            }
            updateUI.run();
        });
        projTimer.start();
    }
 
    public void updateAI() {
        // Bot chases player
        bot.chase(player);
 
        // Bot attacks if close (Melee range)
        if (bot.getDistanceTo(player) < 60) {
            player.takeDamage(1); 
        }
    }
}
