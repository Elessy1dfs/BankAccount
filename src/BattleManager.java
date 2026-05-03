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
