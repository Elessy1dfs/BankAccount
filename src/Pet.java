import java.awt.image.BufferedImage;

public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public BufferedImage spriteSheet;
    public int frameIndex = 0;
    public int state = 0; // 0: Idle, 1: Attack, 2: Hit

    // Position variables for movement
    public int x, y;
    public int baseX, baseY;
}