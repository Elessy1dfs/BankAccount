import javax.swing.*;
import java.awt.*;

public class MemeMonGame extends JFrame {
    // FIX: Marked fields as 'final' to prevent reassignment (Standard OOP Practice)
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContainer = new JPanel(cardLayout);

    public static void main(String[] args) {
        // FIX: Replaced Lambda with Method Reference (SwingUtilities::new)
        SwingUtilities.invokeLater(MemeMonGame::new);
    }

    public MemeMonGame() {
        super("Meme-mon: Brainrot Arena");

        // 1. Set up the Pets
        // Using two words 'set up' for the verb action as suggested by the IDE
        Pet player = new Pet("Sigma", 100, 20, 15, "sigma_sheet.png", 100, 400) {};
        Pet bot = new Pet("Skibidi", 80, 18, 5, "skibidi_sheet.png", 800, 400) {};

        // 2. Set up the Manager
        // Passing the repaint method reference to refresh the GUI
        BattleManager manager = new BattleManager(player, bot, mainContainer::repaint);

        // 3. Set up the Battle Screen
        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);

        JButton attackBtn = new JButton("USE AURA ATTACK");
        attackBtn.setFont(new Font("Arial Black", Font.BOLD, 18));
        attackBtn.setPreferredSize(new Dimension(800, 60)); // Standard dimensions for UI visibility

        // FIX: Replaced Lambda with Method Reference for the action listener
        attackBtn.addActionListener(e -> manager.playerAttack());

        battleScreen.add(arena, BorderLayout.CENTER);
        battleScreen.add(attackBtn, BorderLayout.SOUTH);

        // 4. Set up the Main Menu
        MainMenu menu = new MainMenu(() -> {
            cardLayout.show(mainContainer, "BATTLE");
            arena.requestFocusInWindow();
        });

        // 5. Add screens to the container
        mainContainer.add(menu, "MENU");
        mainContainer.add(battleScreen, "BATTLE");

        // 6. Window Configuration
        this.add(mainContainer);

        // Maximizes the window on start for the project presentation
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 600));

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
