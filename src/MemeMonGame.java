import javax.swing.*;
import java.awt.*;
 
public class MemeMonGame extends JFrame {
    // CardLayout allows us to swap between the Menu and the Battle screen
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContainer = new JPanel(cardLayout);
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemeMonGame::new);
    }
 
    public MemeMonGame() {
        super("Meme-mon: Brainrot Arena");
 
        // 1. Initialize Game Objects
        Pet player = new Pet("Sigma", 100, 25, 10, "sigma_sheet.png", 100, 400) {};
        Pet bot = new Pet("Skibidi", 80, 15, 5, "skibidi_sheet.png", 800, 400) {};
        BattleManager manager = new BattleManager(player, bot, mainContainer::repaint);
 
        // 2. Create the Battle Screen
        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);
        battleScreen.add(arena, BorderLayout.CENTER);
 
        // 3. Create the Main Menu
        // We pass a 'Runnable' (lambda) so the menu can tell the frame to switch screens
        MainMenu menu = new MainMenu(() -> {
            cardLayout.show(mainContainer, "BATTLE");
            arena.requestFocusInWindow(); // Ensures keys work immediately
        });
 
        // 4. Add both screens to the CardLayout container
        mainContainer.add(menu, "MENU");
        mainContainer.add(battleScreen, "BATTLE");
 
        // 5. Frame Configuration
        this.add(mainContainer);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMinimumSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Show the Menu first
        cardLayout.show(mainContainer, "MENU");
        this.setVisible(true);
    }
}
