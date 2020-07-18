package gamePackage;
import java.awt.Graphics2D;

public class GameState extends StateHandler {

    private GameHandler gameHandler;
    private WorldHandler worldHandler;

    public GameState(GameHandler handler) {
        this.gameHandler = handler;
        this.worldHandler = new WorldHandler(gameHandler, "TestWorld2.txt");
        //this.gameHandler.setWorldHandler(worldHandler);
    }

    @Override
    public void update() {
        worldHandler.update();
    }

    @Override
    public void render(Graphics2D g2d) {
        worldHandler.render(g2d);
    }
    
}