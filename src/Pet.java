import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * Pet.java - The abstract base class for all combatants.
 * Implements core OOP principles: Abstraction and Encapsulation.
 */
public abstract class Pet {
    // Encapsulated data fields
    public String name;
    public int hp, maxHp, aura, rizz;
    public int x, y;
    
    // Animation and State management
    public int frameIndex = 0;
    public int state = 0; // 0: Idle, 1: Attack, 2: Hit
    public BufferedImage spriteSheet;

    // Projectile logic for the Ranged/Hit-and-Run system
    public boolean projectileActive = false;
    public int projX, projY;

    public Pet(String name, int hp, int aura, int rizz, String spritePath, int x, int y) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.aura = aura;
        this.rizz = rizz;
        this.x = x;
        this.y = y;

        try {
            this.spriteSheet = ImageIO.read(new File(spritePath));
        } catch (Exception e) {
            System.out.println("CRITICAL: Image not found at " + spritePath);
        }
    }

    /**
     * Standard movement logic.
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * AI Logic: Moves this Pet towards a target Pet.
     */
    public void chase(Pet target) {
        if (this.x < target.x) this.x += 2;
        if (this.x > target.x) this.x -= 2;
        if (this.y < target.y) this.y += 2;
        if (this.y > target.y) this.y -= 2;
    }

    /**
     * Calculates distance between this Pet and another using the Pythagorean theorem.
     */
    public double getDistanceTo(Pet other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Applies damage while accounting for the 'rizz' defense stat.
     */
    public void takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - (rizz / 5));
        this.hp = Math.max(0, this.hp - actualDamage);
    }

    /**
     * Extracts the current animation frame from the spritesheet.
     */
    public BufferedImage getCurrentFrame() {
        if (spriteSheet == null) return getPlaceholder();

        int colWidth = spriteSheet.getWidth() / 4;
        int rowHeight = spriteSheet.getHeight() / 3;
        int currentState = Math.min(Math.max(state, 0), 2);
        
        int yOffset = 40; // Adjust based on your specific Aseprite layout
        int startY = (currentState * rowHeight) + yOffset;
        int finalHeight = rowHeight - yOffset;

        if (startY + finalHeight > spriteSheet.getHeight()) {
            finalHeight = spriteSheet.getHeight() - startY;
        }

        return spriteSheet.getSubimage(
            frameIndex * colWidth, 
            startY, 
            colWidth, 
            Math.max(1, finalHeight)
        );
    }

    /**
     * Safety backup image in case assets fail to load.
     */
    private BufferedImage getPlaceholder() {
        BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, 64, 64);
        g.dispose();
        return img;
    }
}
