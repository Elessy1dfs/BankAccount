import javax.swing.*;
import java.awt.*;

public class MemeMonGame extends JFrame {
    // CardLayout allows us to switch between the Menu and the Battle Screen
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(cardLayout);

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new MemeMonGame());
    }

    public MemeMonGame() {
        super("Meme-mon: Brainrot Arena");

        // 1. Setup the Pets
        // We pass 0, 0 for bx and by because BattlePanel calculates the center dynamically
        Pet player = new Pet("Sigma", 100, 20, 15, "sigma_sheet.png", 100, 400) {};
        Pet bot = new Pet("Skibidi", 80, 18, 5, "skibidi_sheet.png", 800, 400) {};

        // 2. Setup the Manager
        // The lambda () -> mainContainer.repaint() tells the manager to refresh the screen
        BattleManager manager = new BattleManager(player, bot, () -> mainContainer.repaint());

        // 3. Setup the Battle Screen
        // We wrap the BattlePanel and the Attack Button in a single panel
        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);

        JButton attackBtn = new JButton("USE AURA ATTACK");
        attackBtn.setFont(new Font("Arial Black", Font.BOLD, 18));
        attackBtn.setPreferredSize(new Dimension(getWidth(), 60));
        attackBtn.addActionListener(e -> manager.playerAttack());

        battleScreen.add(arena, BorderLayout.CENTER);
        battleScreen.add(attackBtn, BorderLayout.SOUTH);

        // 4. Setup the Main Menu
        // When the start button is clicked, we tell the cardLayout to show "BATTLE"
// Inside MemeMonGame constructor
        MainMenu menu = new MainMenu(() -> {
            cardLayout.show(mainContainer, "BATTLE");
            // This line is critical! It tells the game to listen to the panel, not the buttons.
            arena.requestFocusInWindow();
        });

        // 5. Add screens to the container
        mainContainer.add(menu, "MENU");
        mainContainer.add(battleScreen, "BATTLE");

        // 6. Window Configuration
        this.add(mainContainer);

        // This command fixes the "tiny sliver" window by maximizing it on start
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Sets a minimum size so the layout doesn't break if resized
        this.setMinimumSize(new Dimension(800, 600));

        this.setLocationRelativeTo(null); // Centers the window (if not maximized)
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
