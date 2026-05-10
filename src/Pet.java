
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
 
public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public int mana, maxMana = 100;
    public int x, y;
    public int faceDir = -1;
    public int frameIndex = 0;
    public int state = 0; 
    public BufferedImage spriteSheet;
    
    public Pet(String name, int hp, int aura, int rizz, String spritePath, int x, int y) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mana = maxMana;
        this.aura = aura;
        this.rizz = rizz;
        this.x = x;
        this.y = y;
          try {
        this.spriteSheet = ImageIO.read(new File(spritePath));
    } catch (Exception e) {
        System.out.println("Resource error: " + spritePath);
    }
     
    }
 
      public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        if (dx > 0) faceDir = 1;
        else if (dx < 0) faceDir = -1;
    }
 
      public void chase(Pet target) {
        int speed = 3;
        if (this.x < target.x) { this.x += speed; faceDir = 1; }
        else if (this.x > target.x) { this.x -= speed; faceDir = -1; }
        if (this.y < target.y) this.y += speed;
        else if (this.y > target.y) this.y -= speed;
    }
    
}
