import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public BufferedImage spriteSheet;
    public int frameIndex = 0;
    public int state = 0;

    public int x, y;
    public int baseX, baseY;

    public Pet(String name, int hp, int aura, int rizz, String spritePath, int bx, int by) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.aura = aura;
        this.rizz = rizz;
        this.baseX = bx;
        this.baseY = by;
        this.x = bx;
        this.y = by;

        try {
            this.spriteSheet = ImageIO.read(new File(spritePath));
        } catch (Exception e) {
            System.out.println("CRITICAL: Image not found at " + spritePath);
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - (rizz / 5));
        this.hp = Math.max(0, this.hp - actualDamage);
    }
}