package gamePackage;
import java.awt.Graphics2D;

public class Player extends DynamicSprite {

    private float downSpeed = 2.0f;
    //private int testCount = 0;
    public Player(GameHandler gameHandler, float x, float y, int w, int h) {
        super(gameHandler, x, y, w, h);
        this.gameHandler.setPlayer(this);
    }

    @Override
    public void update() {      

        KeyInputHandler k = gameHandler.getKeyInputHandler();
        velocity.setX(0);

        if(k.DOWN) {
            velocity.addY(downSpeed);
        }

        if(k.RIGHT) {
            velocity.addX(xSpeed);
        }

        if(k.LEFT) {
            velocity.addX(-xSpeed);
        }

        if(k.SPACE) {
            jump();
        }

        velocity.addY(gravity.getY());
        move();

        gameHandler.getCamera().center(this);
    }

    @Override
    public void render(Graphics2D g2d) {
        //its minus a minus so it gets reveresed. this shifts everything to the right as the player
        //moves until they reach the right most side
        g2d.fillRect((int) (pos.getX()-gameHandler.getCamera().getCameraX()), (int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight);

        // if(testCount > 500) {
        //     testCount = 0;
        //     System.out.println(pos.getX());
        //     System.out.println(pos.getY());
        //     System.out.println(gameHandler.getCamera().getCameraX());
        //     System.out.println(gameHandler.getCamera().getCameraY());
        //     System.out.println(pos.getX()-gameHandler.getCamera().getCameraX());
        //     System.out.println(pos.getY()-gameHandler.getCamera().getCameraY());
        // }
        // testCount++;
    }
}