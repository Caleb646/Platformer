package gamePackage;
import java.util.ArrayList;
import java.awt.Graphics2D;

public class SpriteManager {

    private ArrayList<Sprite> currentSprites;

    private GameHandler gameHandler;

    public SpriteManager(GameHandler g) {
        this.gameHandler = g;
        this.gameHandler.setSpriteManager(this);
        currentSprites = new ArrayList<Sprite>();
    }

    public void update() {
        for(Sprite s : currentSprites) {
            s.update();
        }
    }

    public void render(Graphics2D g2d) {
        for(Sprite s : currentSprites) {
            s.render(g2d);
        }
    }

    public void addSprite(Sprite s) {
        currentSprites.add(s);
    }
}