package gamePackage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends DynamicSprite {

    //
    private final int axeWidth = spriteWidth-8;
    private final int axeHeight = spriteHeight-8;

    private float downSpeed = 2.0f;
    //animations
    private Animater aLeft = new Animater(GameAssets.playerLeft);
    private Animater aRight = new Animater(GameAssets.playerRight);
    private Animater aJumpLeft = new Animater(GameAssets.playerJumpLeft);
    private Animater aJumpRight = new Animater(GameAssets.playerJumpRight);


    public Player(GameHandler gameHandler, float x, float y, int w, int h) {
        super(gameHandler, x, y, w, h);
        this.gameHandler.setPlayer(this);
    }

    @Override
    public void update() {      

        KeyInputHandler k = gameHandler.getKeyInputHandler();
        MouseInputHandler m = gameHandler.getMouseInputHandler();
        aLeft.update();
        aRight.update();
        aJumpLeft.update(); 
        aJumpRight.update();
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

        //this has to happen under the camera update
        if(m.CLICK && canAttack()) {
            //System.out.println("Throwing axe");
            //System.out.println(" Cx: "+this.gameHandler.getCamera().getCameraX()+" Cy: "+this.gameHandler.getCamera().getCameraY());
            //System.out.println(" Mx: "+gameHandler.getMouseInputHandler().getLastPressX()+" My: "+this.gameHandler.getCamera().getCameraY());

            Vector2D target = new Vector2D(gameHandler.getMouseInputHandler().getLastPressX()+this.gameHandler.getCamera().getCameraX(),

              this.gameHandler.getMouseInputHandler().getLastPressY()+this.gameHandler.getCamera().getCameraY());

            new PlayerAxe(gameHandler, pos.getX(), pos.getY(), axeWidth, axeHeight, target);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        //its minus a minus so it gets reveresed. this shifts everything to the right as the player
        //moves until they reach the right most side
        g2d.drawImage(getAnimation(), (int) (pos.getX()-gameHandler.getCamera().getCameraX()), (int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight, null);
    }

    public BufferedImage getAnimation() {
        float x = velocity.getX();
        float y = velocity.getY();
        if(y < 0 && x > 0) {//jumping right
            return aJumpRight.getCurrentAnimation();
        }
        else if(y < 0 && x < 0) {// jumping left
            return aJumpLeft.getCurrentAnimation();
        }
        else if(velocity.getX() > 0) {//right
            return aRight.getCurrentAnimation();
        }
        else if(velocity.getX() < 0) {//left
            return aLeft.getCurrentAnimation();
        }
        return GameAssets.playerIdle;
        

    }
}