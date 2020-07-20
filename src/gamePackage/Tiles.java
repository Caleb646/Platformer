package gamePackage;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Tiles {
    //1 floor tile, 0 blank tile
    public static int tileWidth, tileHeight;

    public static Tiles[] tileTypes = new Tiles[5];
    public static Tiles blankTile = new BlankTile(0, 32, 32);
    public static Tiles floorTile = new FloorTile(1, 32, 32);
    public static Tiles spikeTile = new SpikeTile(2, 32, 32);
    public static Tiles enemyTile = new EnemyTile(3, 32, 32);//this is just a placeholder so the world handler knows where to draw the enemies
    public static Tiles flagTile = new FlagTile(4, 32, 32);  
    public static Tiles axeAmmo = new AxeAmmoTile(5, 32, 32);//placeholder
    public static Tiles healthPowerUp = new HealthTile(6, 32, 32);//placeholder

    protected BufferedImage image;
    protected final int id;

    public Tiles(BufferedImage img, int id, int tW, int tH) {
        this.id = id;
        this.image = img;
        tileWidth = tW;
        tileHeight = tH;
        tileTypes[id] = this;
    }

    public void update() {

    }

    public void render(Graphics2D g2d) {

    }

    public static boolean isSolid(int id) {
        if(id == 0)
            return false;
        else {
            return true;
        }
    }

    public boolean isSolid() {
        return false;
    }

    //getters and setters

    public BufferedImage getImage() {
        return this.image;
    }

    public int getId() {
        return this.id;
    }
    
}

class FloorTile extends Tiles {

    public FloorTile(int id, int tW, int tH) {
        super(GameAssets.floorImg, id, tW, tH);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}

class BlankTile extends Tiles {

    public BlankTile(int id, int tW, int tH) {
        super(null, id, tW, tH);
    }
}

class EnemyTile extends Tiles {

    public EnemyTile(int id, int tW, int tH) {
        super(null, id, tW, tH);
    }
}

class SpikeTile extends Tiles {

    public SpikeTile(int id, int tW, int tH) {
        super(GameAssets.spikeImg, id, tW, tH);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}

class FlagTile extends Tiles {

    public FlagTile(int id, int tW, int tH) {
        super(GameAssets.flagImg, id, tW, tH);
    }
}

class AxeAmmoTile extends Tiles {

    public AxeAmmoTile(int id, int tW, int tH) {
        super(null, id, tW, tH);
    }
}

class HealthTile extends Tiles {

    public HealthTile(int id, int tW, int tH) {
        super(null, id, tW, tH);
    }
}