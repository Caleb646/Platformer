package gamePackage;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class Tiles {
    //1 floor tile, 0 blank tile
    public static final int tileWidth = 32, tileHeight = 32;

    public static Tiles[] tileTypes = new Tiles[5];
    public static Tiles blankTile = new BlankTile(0);
    public static Tiles floorTile = new FloorTile(1);

    protected BufferedImage image;
    protected final int id;

    public Tiles(BufferedImage img, int id) {
        this.id = id;
        this.image = img;
        tileTypes[id] = this;
    }

    public void update() {

    }

    public void render(Graphics2D g2d) {

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

    public FloorTile(int id) {
        super(GameAssets.floorImg, id);
    }
    
    @Override
    public boolean isSolid() {
        return true;
    }
}

class BlankTile extends Tiles {

    public BlankTile(int id) {
        super(null, id);
    }
}