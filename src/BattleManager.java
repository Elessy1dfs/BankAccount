import javax.swing.Timer;

public class BattleManager {
    public Pet player, bot;
    public boolean playerTurn = true;
    private Runnable updateUI;

    public BattleManager(Pet player, Pet bot, Runnable updateUI) {
        this.player = player;
        this.bot = bot;
        this.updateUI = updateUI;
    }
}
public void playerAttack() {
        if (!playerTurn || player.hp <= 0 || bot.hp <= 0) return;

        playerTurn = false;
        player.state = 1; // Attacking state
        player.x += 50; // Dash toward bot

        bot.takeDamage(player.aura);
        bot.state = 2; // Hurt state

        updateUI.run();

        // Reset player position after 500ms
        Timer resetPos = new Timer(500, e -> {
            player.x = player.baseX;
            updateUI.run();
        });
        resetPos.setRepeats(false);
        resetPos.start();

        // Trigger bot turn after 1.2 seconds
        Timer timer = new Timer(1200, e -> executeBotTurn());
        timer.setRepeats(false);
        timer.start();
    }
private void executeBotTurn() {
        if (bot.hp <= 0) return;

        bot.state = 1; // Bot attacking
        player.takeDamage(bot.aura);
        player.state = 2; // Player hurt

        updateUI.run();

        // Return both to Idle (state 0) after 600ms
        Timer reset = new Timer(600, e -> {
            player.state = 0;
            bot.state = 0;
            playerTurn = true;
            updateUI.run();
        });
        reset.setRepeats(false);
        reset.start();
    }

