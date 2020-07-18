package gamePackage;
import java.awt.Graphics2D;

public class WorldHandler {
    //0 open space, 1 floor
    private GameHandler gameHandler;
    private SpriteManager spriteManager;

    private int gameWidth;//in units
    private int gameHeight;
    private String currentLvl;
    private int[][] worldTiles;
    
    public WorldHandler(GameHandler handler, String path) {
        this.gameHandler = handler;
        this.gameHandler.setWorldHandler(this);


        PathFinder pather = new PathFinder(gameHandler);
        Player p = new Player(gameHandler, 100, 100, 20, 20);
        Enemy e = new Enemy(gameHandler, 400, 100, 20, 20);


        spriteManager = new SpriteManager(gameHandler);
        spriteManager.addSprite(p);
        spriteManager.addSprite(e);

        currentLvl = path;
        loadLevel();

        
        
        
    }

    public void update() {
        spriteManager.update();
    }

    public void render(Graphics2D g2d) {
        for(int x = 0; x<gameWidth; x++) {
            for(int y = 0; y<gameHeight; y++) {
                if(getTileType(x, y).getImage() == null)
                    continue;
                g2d.drawImage(getTileType(x, y).getImage(), (int) (x*Tiles.tileWidth-gameHandler.getCamera().getCameraX()),
                (int) (y*Tiles.tileWidth-gameHandler.getCamera().getCameraY()), Tiles.tileWidth, Tiles.tileHeight, null);
            }
        }
        spriteManager.render(g2d);
    }

    public void loadLevel() {
        String[] lvlInfo = Utility.loadFile(currentLvl).split("\\s+");
        gameWidth = Integer.parseInt(lvlInfo[0]);
        gameHeight = Integer.parseInt(lvlInfo[1]);
        worldTiles = new int[gameWidth][gameHeight];
        for(int x = 0; x<gameWidth; x++) {
            for(int y = 0; y<gameHeight; y++) {
                worldTiles[x][y] = Integer.parseInt(lvlInfo[(x+y*gameWidth)+2]);
            }
        }
        this.gameHandler.getPathFinder().createNodes();//creates node and edges graph
    }

    //getters and setters

    public void setCurrentLevel(String path) {
        currentLvl = path;
        loadLevel();
    }

    public Tiles getTileType(int x, int y) {
        if(x < 0 || y < 0 || x >= gameWidth || y >= gameHeight)
            return Tiles.blankTile;
        return Tiles.tileTypes[worldTiles[x][y]];
    }

    public int getGameWidth() {
        return this.gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

}