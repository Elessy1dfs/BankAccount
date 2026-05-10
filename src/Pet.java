public abstract class Pet {
    public String name;
    public int hp, maxHp, aura, rizz;
    public int mana, maxMana = 100;
    public int x, y;
    public int faceDir = -1;
 
    public Pet(String name, int hp, int aura, int rizz, String spritePath, int x, int y) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.mana = maxMana;
        this.aura = aura;
        this.rizz = rizz;
        this.x = x;
        this.y = y;
    }
}
