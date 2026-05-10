public class BattlePanel extends JPanel {
    private final BattleManager manager;
    private Image playerPlasma, enemyPlasma, victoryImg, defeatedImg, hpPotionImg, manaPotionImg;
    private Image[] backgrounds = new Image[5];
 
    public BattlePanel(BattleManager manager) {
        this.manager = manager;
        this.setFocusable(true);
        try {
            playerPlasma = ImageIO.read(new File("plasma_ball.png"));
            enemyPlasma = ImageIO.read(new File("plasma_ball.png"));
            victoryImg = ImageIO.read(new File("victory.png"));
            defeatedImg = ImageIO.read(new File("defeated.png"));
            hpPotionImg = ImageIO.read(new File("hp_potion.png"));
            manaPotionImg = ImageIO.read(new File("mana_potion.png"));
            backgrounds[1] = ImageIO.read(new File("prelim_bg.png"));
            backgrounds[2] = ImageIO.read(new File("midterm_bg.png"));
            backgrounds[3] = ImageIO.read(new File("prefinal_bg.png"));
            backgrounds[4] = ImageIO.read(new File("final_bg.png"));
        } catch (Exception e) { e.printStackTrace(); }
    }
}
