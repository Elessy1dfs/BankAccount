import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public BufferedImage spriteSheet;
    public int frameIndex = 0;
    public int state = 0; // 0: Idle, 1: Attack, 2: Hit

    // Position variables for movement
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

    public BufferedImage getCurrentFrame() {
        if (spriteSheet == null) return getPlaceholder();

        int colWidth = spriteSheet.getWidth() / 4;
        int rowHeight = spriteSheet.getHeight() / 3;
        int currentState = Math.min(Math.max(state, 0), 2);

        int startY = currentState * rowHeight;
        int yOffset = 40; // Skips text labels in your sprites

        int finalY = startY + yOffset;
        int finalHeight = rowHeight - yOffset;

        if (finalY + finalHeight > spriteSheet.getHeight()) {
            finalHeight = spriteSheet.getHeight() - finalY;
        }

        return spriteSheet.getSubimage(
                frameIndex * colWidth,
                finalY,
                colWidth,
                Math.max(1, finalHeight)
        );
    }

    private BufferedImage getPlaceholder() {
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, 64, 64);
        g.dispose();
        return img;
    }
}