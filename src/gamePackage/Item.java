package gamePackage;
import java.awt.Graphics2D;

public class Item {

    protected GameHandler gameHandler;

    protected final int itemWidth = 24, itemHeight = 24;

    protected int posX;
    protected int posY;

    protected boolean pickedUp = false;
    
    public Item(GameHandler gm, int x, int y) {
        this.gameHandler = gm;
        this.posX = x;
        this.posY = y;
    }

    public void update() {

    }

    public void render(Graphics2D g2d) {

    }

    //getters and setters

    public void setPickedUp(boolean b) {
        this.pickedUp = b;
    }

    public boolean isPickedUp() {
        return this.pickedUp;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }
}

class AmmoPickUp extends Item {

	public AmmoPickUp(GameHandler gm, int x, int y) {
        super(gm, x, y);
        //TODO create Item Manager like Sprite Manager and 
        // add this to it when created
    }
    
    public void update() {

    }

    public void render(Graphics2D g2d) {
        g2d.drawImage(GameAssets.pAxe, posX, posY, itemWidth, itemHeight, null);
    }
}