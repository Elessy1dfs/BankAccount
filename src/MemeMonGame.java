import javax.swing.*;
import java.awt.*;

public class MemeMonGame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContainer = new JPanel(cardLayout);

    public static void main(String[] args) {
        // Use Method Reference for cleaner startup
        SwingUtilities.invokeLater(MemeMonGame::new);
    }

    public MemeMonGame() {
        super("Meme-mon: Brainrot Arena");

        // 1. Set up the Pets with initial spawn positions
        Pet player = new Pet("Sigma", 100, 20, 15, "sigma_sheet.png", 100, 400) {};
        Pet bot = new Pet("Skibidi", 80, 18, 5, "skibidi_sheet.png", 800, 400) {};

        // 2. Set up the Manager
        BattleManager manager = new BattleManager(player, bot, mainContainer::repaint);

        // 3. Set up the Battle Screen (Removed attackBtn)
        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);
        battleScreen.add(arena, BorderLayout.CENTER);

        // 4. Set up the Main Menu with focus request
        MainMenu menu = new MainMenu(() -> {
            cardLayout.show(mainContainer, "BATTLE");
            arena.requestFocusInWindow(); // Critical for Spacebar/Arrow keys to work
        });

        // 5. Add screens to the container
        mainContainer.add(menu, "MENU");
        mainContainer.add(battleScreen, "BATTLE");

        // 6. Window Configuration
        this.add(mainContainer);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
