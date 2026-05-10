import javax.swing.*;
import java.awt.*;

public class MemeMonGame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainContainer = new JPanel(cardLayout);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemeMonGame::new);
    }

    public MemeMonGame() {
        super("Meme-mon: Academic Brainrot Arena");

        Pet player = new Pet("Sigma", 150, 30, 20, "sigma_sheet.png", 100, 400) {};

        BattleManager manager = new BattleManager(player, mainContainer::repaint);

        JPanel battleScreen = new JPanel(new BorderLayout());
        BattlePanel arena = new BattlePanel(manager);
        battleScreen.add(arena, BorderLayout.CENTER);

        MainMenu menu = new MainMenu(() -> {
            cardLayout.show(mainContainer, "BATTLE");
            arena.requestFocusInWindow();
        });

        mainContainer.add(menu, "MENU");
        mainContainer.add(battleScreen, "BATTLE");

        this.add(mainContainer);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout.show(mainContainer, "MENU");
        this.setVisible(true);
    }
}
