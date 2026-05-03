import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public int x, y;
    public int frameIndex = 0;
    public int state = 0; 
    public BufferedImage spriteSheet;

    public boolean projectileActive = false;
    public int projX, projY;
    public int projDir = 1; 

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
            System.out.println("Image not found: " + spritePath);
        }
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void chase(Pet target) {
        if (this.x < target.x) this.x += 2;
        if (this.x > target.x) this.x -= 2;
        if (this.y < target.y) this.y += 2;
        if (this.y > target.y) this.y -= 2;
    }

    public double getDistanceTo(Pet other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
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
        int yOffset = 40; 
        int startY = (currentState * rowHeight) + yOffset;
        int finalHeight = rowHeight - yOffset;
        return spriteSheet.getSubimage(frameIndex * colWidth, startY, colWidth, Math.max(1, finalHeight));
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
