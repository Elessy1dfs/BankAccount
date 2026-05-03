import javax.swing.*;
import java.awt.*;
 
public class MemeMonGame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContainer = new JPanel(cardLayout);
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemeMonGame::new);
    }
 
    public MemeMonGame() {
        super("Meme-mon: Brainrot Arena");
        Pet player = new Pet("Sigma", 100, 25, 10, "sigma_sheet.png", 100, 400) {};
        Pet bot = new Pet("Skibidi", 80, 15, 5, "skibidi_sheet.png", 800, 400) {};
 
        BattleManager manager = new BattleManager(player, bot, mainContainer::repaint);
        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);
        battleScreen.add(arena, BorderLayout.CENTER);
 
        // Assuming you have a MainMenu class defined elsewhere
        // If not, you can just add(battleScreen) directly to this frame
        this.add(battleScreen);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
