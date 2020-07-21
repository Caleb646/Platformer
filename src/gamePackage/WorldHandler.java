package gamePackage;
import java.awt.Graphics2D;

public class WorldHandler {
    //0 open space, 1 floor
    private GameHandler gameHandler;
    private SpriteManager spriteManager;
    private ItemManager itemManager;

    private int gameWidth;//in units
    private int gameHeight;
    private String currentLvl;
    private int[][] worldTiles;
    
    public WorldHandler(GameHandler handler, String path) {
        this.gameHandler = handler;
        this.gameHandler.setWorldHandler(this);


        PathFinder pather = new PathFinder(gameHandler);
        Player p = new Player(gameHandler, 500, 100, 28, 28);
        


        spriteManager = new SpriteManager(gameHandler);
        spriteManager.addSprite(p);

        itemManager = new ItemManager(gameHandler);      

        currentLvl = path;
        loadLevel();         
    }

    public void update() {
        spriteManager.update();
        itemManager.update();
    }

    public void render(Graphics2D g2d) {
        //checks to see where the player is in relation to the map and then only renders that area
        int xStart = (int) Math.max(0, gameHandler.getCamera().getCameraX() / Tiles.tileWidth);
        int xEnd = (int) Math.min(gameWidth, (gameHandler.getCamera().getCameraX() + gameHandler.getGameWidth()) / Tiles.tileWidth + 1);
        int yStart = (int) Math.max(0, gameHandler.getCamera().getCameraY() / Tiles.tileHeight);
        int yEnd = (int) Math.min(gameHeight, (gameHandler.getCamera().getCameraY() + gameHandler.getGameHeight()) / Tiles.tileHeight + 1);
        for(int y = yStart; y<yEnd; y++) {
            for(int x = xStart; x<xEnd; x++) {
                if(getTileType(x, y).getImage() == null)
                    continue;
                g2d.drawImage(getTileType(x, y).getImage(), (int) (x*Tiles.tileWidth-gameHandler.getCamera().getCameraX()),
                (int) (y*Tiles.tileWidth-gameHandler.getCamera().getCameraY()), Tiles.tileWidth, Tiles.tileHeight, null);
            }
        }
        spriteManager.render(g2d);
        itemManager.render(g2d);
    }

    public void loadLevel() {

        String[] lvlInfo = Utility.loadFile(currentLvl).split("\\s+");
        gameWidth = Integer.parseInt(lvlInfo[0]);
        gameHeight = Integer.parseInt(lvlInfo[1]);
        worldTiles = new int[gameWidth][gameHeight];

        for(int y = 0; y<gameHeight; y++) {
            for(int x = 0; x<gameWidth; x++) {

                int tileId = Integer.parseInt(lvlInfo[(x+y*gameWidth)+2]);
                worldTiles[x][y] = tileId;

                switch(tileId) {

                case 3: {//striker enemy
                    Enemy e = new StrikerEnemy(gameHandler, x*Tiles.tileWidth, y*Tiles.tileHeight, 28, 28);
                    spriteManager.addSprite(e);
                    break;
                }
                case 5:{//ammo pickup
                    Item ammoPickUp = new AmmoPickUp(gameHandler, x*Tiles.tileWidth, y*Tiles.tileHeight, tileId);
                    itemManager.addItem(ammoPickUp);
                    break;
                }
                case 6: {//health pickup
                    Item healthPickUp = new HealthPickUp(gameHandler, x*Tiles.tileWidth, y*Tiles.tileHeight, tileId);
                    itemManager.addItem(healthPickUp);
                    break;
                }
                case 7: {
                    Enemy e = new TurretEnemy(gameHandler, x*Tiles.tileWidth, y*Tiles.tileHeight, 32, 32);
                    spriteManager.addSprite(e);
                    break;
                }
            }
        }     
    }
    this.gameHandler.getPathFinder().createNodes();  //creates node and edges graph 
    //this.gameHandler.getPathFinder().printGraph();
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