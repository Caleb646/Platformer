package gamePackage;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class PlayerAxe extends DynamicSprite {

    private float ySpeed = 2.75f;//unit speed
    private float xSpeed = 2.75f;//unit speed
    private float maxYSpeed = 7.0f;
    private float maxXSpeed = 7.0f;
    private Vector2D target;

    //animations
    private Animater aRight = new Animater(GameAssets.pAxeRight);
    private Animater aLeft = new Animater(GameAssets.pAxeLeft);

    public PlayerAxe(GameHandler gameHandler, float x, float y, int w, int h, Vector2D target) {
        super(gameHandler, x, y, w, h);
        this.target = target;
        aRight.setInterval(100);
        aLeft.setInterval(100);
        gameHandler.getSpriteManager().addSprite(this); //add this so it can be draw/rendered
        
    }

    public void update() {
        aRight.update();
        aLeft.update();
        Vector2D normedV = pos.normalize(target);
        velocity.addX(normedV.getX()*xSpeed);
        velocity.addY(normedV.getY()*ySpeed);
        this.move();
    }

    public void render(Graphics2D g2d) {


        g2d.drawImage(getAnimation(), (int) (pos.getX()-gameHandler.getCamera().getCameraX()),
        (int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight, null);

        //g2d.fillRect((int) (pos.getX()-gameHandler.getCamera().getCameraX()),(int) (pos.getY()-gameHandler.getCamera().getCameraY()), spriteWidth, spriteHeight);
    }

    public BufferedImage getAnimation() {

        if(velocity.getX() > 0)
            return aRight.getCurrentAnimation();
        else if(velocity.getX() < 0)
            return aLeft.getCurrentAnimation();
        return aLeft.getCurrentAnimation();
    }

    @Override
    public void move() {
        boolean bX = canMoveX();
        boolean bY = canMoveY();
        //System.out.println(pos.distance(target));
        if(!bX || !bY || pos.distance(target) <= 8) {
           setAlive(false);
           return;
        }
        if(bX) {
            pos.addX(velocity.getX());
           
        }
        if(bY) {
            pos.addY(velocity.getY());
        }

        if(velocity.getY() > maxYSpeed) {
            velocity.setY(maxYSpeed);
        }
        if(velocity.getX() > maxXSpeed) {
            velocity.setX(maxXSpeed);
        }
    }
    
}